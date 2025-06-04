package io.github.alexsarrell.arch.core.pipeline.pipe.impl

import io.github.alexsarrell.arch.core.parser.SpecParser
import io.github.alexsarrell.arch.core.pipeline.context.PipelineContext
import io.github.alexsarrell.arch.core.pipeline.context.specLimit
import io.github.alexsarrell.arch.core.pipeline.pipe.ChildPipe
import io.github.alexsarrell.arch.core.pipeline.pipe.context.LoadPipeContext
import io.github.alexsarrell.arch.core.pipeline.pipe.context.ParsePipeContext
import io.github.alexsarrell.arch.core.pipeline.pipe.context.PipeContext
import io.github.alexsarrell.arch.core.pipeline.pipe.context.specFiles

class ParsePipe(
    private val parser: SpecParser,
) : ChildPipe<LoadPipeContext>(LoadPipeContext::class.java) {
    private lateinit var pipeContext: ParsePipeContext

    override fun pipeContext(): PipeContext {
        return pipeContext
    }

    override fun PipelineContext.process(parentContext: LoadPipeContext) {
        pipeContext = ParsePipeContext(this)
        parser.parse(
            parentContext.specFiles,
            parentContext.pipelineContext.specLimit,
            pipeContext,
        )
    }
}
