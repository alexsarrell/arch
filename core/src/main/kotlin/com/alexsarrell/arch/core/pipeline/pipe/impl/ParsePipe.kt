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
