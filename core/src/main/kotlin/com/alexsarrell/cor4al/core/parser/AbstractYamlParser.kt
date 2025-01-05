package com.alexsarrell.cor4al.core.parser

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
    private val yaml =
        Yaml(
            configuration =
                YamlConfiguration(
                    strictMode = strictMode,
                ),
        )
    private val parsedSpecs = mutableMapOf<String, Spec>()

    // TODO cache for optimisation
    private fun parseFile(file: File): Spec {
        val parseResult = parsedSpecs[file.path]
            ?: yaml.decodeFromString(Spec.serializer(), file.readText())
                .also { parsedSpecs[file.path] = it }

        parseResult.schemas.forEach { (_, schema) ->
            val parentFile = schema.parent?.resolveParentFile(file)

            if (parentFile == file) {
                schema.parent.resolveRef(parseResult)
            } else if (parentFile != null) {
                val parentParseResult = parseFile(parentFile)
                schema.parent.resolveRef(parentParseResult)
            }

            context.addSpec(parseResult)
        }
        return parseResult
    }

    override fun parse(
        specs: Set<File>,
        specsLimit: List<String>,
    ) {
        val files =
            specs
                .let { files ->
                    if (specsLimit.isEmpty()) return@let files
                    files.filter { file ->
                        specsLimit.any { file.path.endsWith(it) }
                    }
                }

        files.forEach { parseFile(it) }
    }

    override fun context(): PipeContext = context
}
