package com.alexsarrell.arch.core.parser

import com.alexsarrell.arch.core.pipeline.pipe.context.ParsePipeContext
import com.alexsarrell.arch.core.pipeline.pipe.context.PipeContext
import java.io.File

interface SpecParser {

    fun parse(specs: Set<File>, specsLimit: List<String>, context: ParsePipeContext)
}
