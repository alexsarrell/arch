package com.alexsarrell.arch.core.pipeline.pipe.impl

import com.alexsarrell.arch.core.parser.SpecParser
import com.alexsarrell.arch.core.pipeline.context.PipelineContext
import com.alexsarrell.arch.core.pipeline.context.specLimit
import com.alexsarrell.arch.core.pipeline.pipe.ChildPipe
import com.alexsarrell.arch.core.pipeline.pipe.context.LoadPipeContext
import com.alexsarrell.arch.core.pipeline.pipe.context.ParsePipeContext
import com.alexsarrell.arch.core.pipeline.pipe.context.PipeContext
import com.alexsarrell.arch.core.pipeline.pipe.context.specFiles

class ParsePipe(
    context: PipelineContext,
    private val parser: SpecParser,
) : ChildPipe<LoadPipeContext>(LoadPipeContext::class.java) {
    override val pipeContext: ParsePipeContext = ParsePipeContext(context)

    override fun process(parentContext: LoadPipeContext) {
        parser.parse(
            parentContext.specFiles,
            parentContext.pipelineContext.specLimit,
            pipeContext,
        )
    }
}
