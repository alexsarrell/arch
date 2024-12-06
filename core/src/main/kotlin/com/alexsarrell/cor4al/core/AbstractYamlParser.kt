package com.alexsarrell.cor4al.core

import com.alexsarrell.cor4al.core.model.SchemaDefinition
import com.alexsarrell.cor4al.core.model.SchemaProperty
import com.alexsarrell.cor4al.core.model.SettingsDefinition
import com.alexsarrell.cor4al.core.model.Spec
import com.alexsarrell.cor4al.core.serializer.*
import com.charleskorn.kaml.Yaml
import com.charleskorn.kaml.YamlConfiguration
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.contextual
import java.io.File

abstract class AbstractYamlParser(
    private val context: SerializationContext,
    private val typeMappings: Map<String, String>,
    private val validator: SchemaPropertyValidator,
    strictMode: Boolean = false,
) : SpecParser {
    protected abstract val basicTypeMappings: Map<String, String>

    private lateinit var validatedMappings: Map<String, String>

    @Volatile
    private var isValidated: Boolean = false

    private val specTree: MutableMap<String?, MutableList<String>> = mutableMapOf()

    private val yaml = Yaml(
        configuration = YamlConfiguration(
            strictMode = strictMode,
        ),
        serializersModule = SerializersModule {
            contextual(PropertyTypeSerializer(validatedMappings))
            contextual(PropertyValueSerializer())
            contextual(VersionSerializer())
            contextual(DependsOnSerializer(context))
            contextual(ImplementsSerializer(context))
        }
    )

    protected abstract fun validateMappings(typeMappings: Map<String, String>)

    override fun loadFiles(files: List<File>) {
        files.forEach { file ->
            val rawSpec = file.readText()
            val specSettings = yaml.decodeFromString(SettingsDefinition.serializer(), rawSpec)
            if (specTree.containsKey(specSettings.dependsOn)) {
                specTree[specSettings.dependsOn]?.add(rawSpec)
            } else {
                specTree[specSettings.dependsOn] = mutableListOf(rawSpec)
            }
        }
        readTree(null)
    }

    private fun readTree(specName: String?) {
        specTree[specName]?.forEach { readTree(parse(it).settings.name) }
    }

    private fun validateProperties(properties: List<SchemaProperty>) {
        properties.forEach { validator.validate(it) }
    }

    private fun parse(content: String): Spec {
        if (!isValidated) throw IllegalStateException("Specification is not fully validated.")

        val resultSpec = Spec(
            settings = yaml.decodeFromString(SettingsDefinition.serializer(), content),
            schemas = yaml.decodeFromString(SchemaDefinition.serializer(), content),
        )
        val properties = resultSpec.schemas.definition.entries.flatMap { it.value.properties.values }
        validateProperties(properties)
        context.load(resultSpec)
        return resultSpec
    }

    private fun buildMappings(mappings: Map<String, String>): Map<String, String> {
        return mappings.entries.associate { it.key.lowercase() to it.value }
    }

    fun validate() {
        if (isValidated) return
        buildMappings(basicTypeMappings + typeMappings)
            .let {
                validateMappings(it)
                validatedMappings = it
            }
        isValidated = true
    }
}
