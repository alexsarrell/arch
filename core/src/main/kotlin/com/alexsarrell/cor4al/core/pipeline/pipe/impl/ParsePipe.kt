package com.alexsarrell.cor4al.core.pipeline.pipe.impl

import com.alexsarrell.cor4al.core.parser.SpecParser
import com.alexsarrell.cor4al.core.pipeline.context.PipelineContext
import com.alexsarrell.cor4al.core.pipeline.context.specLimit
import com.alexsarrell.cor4al.core.pipeline.pipe.ChildPipe
import com.alexsarrell.cor4al.core.pipeline.pipe.context.LoadPipeContext
import com.alexsarrell.cor4al.core.pipeline.pipe.context.ParsePipeContext
import com.alexsarrell.cor4al.core.pipeline.pipe.context.PipeContext
import com.alexsarrell.cor4al.core.pipeline.pipe.context.specFiles

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
