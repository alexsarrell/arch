package com.alexsarrell.cor4al.core.model

import com.alexsarrell.cor4al.core.model.ModelAccessor.MetadataEntry
import com.alexsarrell.cor4al.core.model.ModelAccessor.ModelProperty
import com.alexsarrell.cor4al.core.pipeline.context.*
import com.alexsarrell.cor4al.core.pipeline.pipe.context.ClassSchema

data class ModelAccessor(
    val className: String,
    val description: String?,
    val parent: String?,
    val parentProperties: List<ModelProperty>,
    val version: String,
    val packageName: String,
    val properties: List<ModelProperty>,
    val isOpen: Boolean,
    val parentPackage: String?,
    val imports: List<String>?,
    val metadata: List<MetadataEntry>?,
    val metadataAccessors: Boolean,
) {
    data class MetadataEntry(
        val key: String,
        val value: String,
    )

    data class ModelProperty(
        val name: String,
        val required: Boolean,
        val description: String?,
        val type: String,
        val version: String,
        val defaultValue: String? = null,
    )

    constructor(schema: ClassSchema, context: PipelineContext) :
        this(
            className = schema.first,
            parent = schema.second.parent?.className,
            version = schema.second.version,
            properties = schema.second.properties.toModelProperties(),
            parentProperties = schema.second.extractParentFields().toModelProperties(),
            packageName = context.packageName,
            isOpen = schema.second.isOpen(),
            parentPackage = context.parentPackage,
            imports = schema.second.imports(context),
            metadata = schema.second.metadata?.toMetadataEntry(),
            description = schema.second.description,
            metadataAccessors = context.metadataAccessors,
        )
}

private fun Map<String, String>.toMetadataEntry() = map { MetadataEntry(it.key, it.value) }

private fun Schema.extractParentFields(): Map<String, SchemaProperty> {
    val fields = mutableMapOf<String, SchemaProperty>()

    fun getParentProperties(parent: Schema?) {
        if (parent == null) return
        fields += parent.properties
        getParentProperties(parent.parent?.resolvedRef)
    }

    getParentProperties(parent?.resolvedRef)
    return fields
}

private fun Map<String, SchemaProperty>.toModelProperties(): List<ModelProperty> =
    entries.map {
        ModelProperty(
            name = it.key,
            required = it.value.required,
            description = it.value.description,
            type = it.value.type,
            version = it.value.version,
            defaultValue = it.value.defaultValue,
        )
    }

private fun Schema.imports(context: PipelineContext): List<String> {
    val propertiesImports = properties.values.mapNotNull { context.importMappings[it.type] }
    val parentPropertiesImports = extractParentFields().values.mapNotNull { context.importMappings[it.type] }
    return propertiesImports.plus(parentPropertiesImports).plus(imports)
}

private fun Schema.isOpen(): Boolean {
    return when (type) {
        SchemaType.ABSTRACT -> true
        else -> false
    }
}
