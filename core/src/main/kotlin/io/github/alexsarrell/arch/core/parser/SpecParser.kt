package io.github.alexsarrell.arch.core.parser

import io.github.alexsarrell.arch.core.pipeline.pipe.context.ParsePipeContext
import io.github.alexsarrell.arch.core.pipeline.pipe.context.PipeContext
import java.io.File

interface SpecParser {

    fun parse(specs: Set<File>, specsLimit: List<String>, context: ParsePipeContext)
}
