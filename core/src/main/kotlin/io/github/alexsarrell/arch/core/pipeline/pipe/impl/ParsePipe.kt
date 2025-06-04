package io.github.alexsarrell.arch.core.pipeline.pipe.impl

import io.github.alexsarrell.arch.core.parser.SpecParser
import io.github.alexsarrell.arch.core.pipeline.context.specLimit
import io.github.alexsarrell.arch.core.pipeline.pipe.AdjacentPipe
import io.github.alexsarrell.arch.core.pipeline.pipe.context.LoadPipeContext
import io.github.alexsarrell.arch.core.pipeline.pipe.context.ParsePipeContext
import io.github.alexsarrell.arch.core.pipeline.pipe.context.specFiles

class ParsePipe(
    private val parser: SpecParser,
) : AdjacentPipe<LoadPipeContext, ParsePipeContext>() {

    override fun process(context: LoadPipeContext): ParsePipeContext {
        parser.parse(
            context.specFiles,
            context.pipelineContext.specLimit,
            pipeContext,
        )
    }
}
