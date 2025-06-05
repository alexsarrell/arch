package io.github.alexsarrell.arch.core.parser

import io.github.alexsarrell.arch.core.pipeline.pipe.context.ParsePipeContext
import java.io.File

interface YamlParser {
    fun parseSpecs(specs: Set<File>, specsLimit: List<String>, context: ParsePipeContext)
    fun parseMappings(mappings: String, context: ParsePipeContext)
}
