package com.alexsarrell.arch.core.parser

import com.alexsarrell.arch.core.model.Spec
import com.alexsarrell.arch.core.pipeline.pipe.context.ParsePipeContext
import com.alexsarrell.arch.core.pipeline.pipe.context.PipeContext
import com.charleskorn.kaml.Yaml
import com.charleskorn.kaml.YamlConfiguration
import java.io.File

abstract class AbstractYamlParser(
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
    private fun parseFile(file: File, context: ParsePipeContext): Spec {
        val parseResult = parsedSpecs[file.path]
            ?: yaml.decodeFromString(Spec.serializer(), file.readText())
                .also { parsedSpecs[file.path] = it }

        parseResult.schemas.forEach { (_, schema) ->
            val parentFile = schema.parent?.resolveParentFile(file)

            if (parentFile == file) {
                schema.parent.resolveRef(parseResult)
            } else if (parentFile != null) {
                val parentParseResult = parseFile(parentFile, context)
                schema.parent.resolveRef(parentParseResult)
            }
        }
        return parseResult
    }

    override fun parse(
        specs: Set<File>,
        specsLimit: List<String>,
        context: ParsePipeContext,
    ) {
        val files =
            specs
                .let { files ->
                    if (specsLimit.isEmpty()) return@let files
                    files.filter { file ->
                        specsLimit.any {
                            file.name == it
                        }
                    }.also {
                        if (it.isEmpty()) {
                            throw IllegalArgumentException("No files matching specified limit: $specsLimit")
                        }
                    }
                }

        files.forEach {
            context.addSpec(
                parseFile(it, context)
            )
        }
    }
}
