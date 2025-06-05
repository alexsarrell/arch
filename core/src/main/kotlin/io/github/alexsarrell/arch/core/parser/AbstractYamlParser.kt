package io.github.alexsarrell.arch.core.parser

import com.charleskorn.kaml.Yaml
import com.charleskorn.kaml.YamlConfiguration
import io.github.alexsarrell.arch.core.model.ImportMappings
import io.github.alexsarrell.arch.core.model.Spec
import io.github.alexsarrell.arch.core.pipeline.pipe.context.ParsePipeContext
import io.github.alexsarrell.arch.core.pipeline.pipe.context.importMappings
import java.io.File

abstract class AbstractYamlParser(
    strictMode: Boolean = false,
) : YamlParser {
    private val yaml =
        Yaml(
            configuration =
                YamlConfiguration(
                    strictMode = strictMode,
                ),
        )
    private val parsedSpecs = mutableMapOf<String, Spec>()

    override fun parseSpecs(
        specs: Set<File>,
        specsLimit: List<String>,
        context: ParsePipeContext,
    ) {
        filterSpecs(specs, specsLimit).forEach {
            context.addSpec(
                parseFile(it, context),
            )
        }
    }

    override fun parseMappings(
        mappings: String,
        context: ParsePipeContext,
    ) {
        context.importMappings = yaml.decodeFromString(ImportMappings.serializer(), mappings).importMappings
    }

    private fun filterSpecs(
        specs: Set<File>,
        specsLimit: List<String>,
    ): Iterable<File> {
        if (specsLimit.isEmpty()) return specs
        return specs
            .filter { file ->
                specsLimit.any {
                    file.name == it
                }
            }.also {
                if (it.isEmpty()) {
                    throw IllegalArgumentException("No files matching specified limit: $specsLimit")
                }
            }
    }

    // TODO cache for optimisation
    private fun parseFile(
        file: File,
        context: ParsePipeContext,
    ): Spec {
        val parseResult =
            parsedSpecs[file.path]
                ?: yaml
                    .decodeFromString(Spec.serializer(), file.readText())
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
}
