package com.alexsarrell.cor4al.core.parser

import com.alexsarrell.cor4al.core.SpecParser
import com.alexsarrell.cor4al.core.model.Spec
import com.alexsarrell.cor4al.core.pipeline.pipe.context.ParsePipeContext
import com.alexsarrell.cor4al.core.pipeline.pipe.context.PipeContext
import com.charleskorn.kaml.Yaml
import com.charleskorn.kaml.YamlConfiguration
import java.io.File

abstract class AbstractYamlParser(
    private val context: ParsePipeContext,
    strictMode: Boolean = false,
) : SpecParser {
    private val yaml = Yaml(
        configuration = YamlConfiguration(
            strictMode = strictMode,
        ),
    )

    protected abstract fun validateMappings(typeMappings: Map<String, String>)

    // Подумать, как оптимизировать
    private fun parseFile(file: File): Spec? {
        val parseResult = yaml.decodeFromString(Spec.serializer(), file.readText())
        parseResult.schemas.forEach { (_, schema) ->
            val parentFile = schema.parent?.resolveParentFile(file)

            if (parentFile == file) {
                schema.parent.resolveRef(parseResult)
            } else if (parentFile != null) {
                val parentParseResult = parseFile(parentFile)
                if (parentParseResult != null) {
                    schema.parent.resolveRef(parentParseResult)
                }
            }

            context.addSpec(parseResult)
        }
        return parseResult
    }

    override fun loadSpecs(specs: Set<File>, specsLimit: List<String>) {
        val files = specs
            .associateBy { it.path }
            .let { entries ->
                if (specsLimit.isEmpty()) return@let entries
                entries.filter { (filePath, _) ->
                    specsLimit.any { filePath.endsWith(it) }
                }
            }

        files.forEach { (_, file) -> parseFile(file) }

        """
            Отфильтровали. Далее. Нам нужно итерируясь по каждому из отфильтрованных файлов:
            1. Резолвить ссылку.
            Ссылки внутри спек нужно резолвить не костылями, а через RefResolver класс, который, по сути, получает на вход Spec, и через рефлексию собирает ссылку к Schema или другому объекту
        """.trimIndent()
    }

    override fun context(): PipeContext {
        return context
    }
}
