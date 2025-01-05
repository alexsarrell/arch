package com.alexsarrell.cor4al.core.pipeline.helper

import com.alexsarrell.cor4al.core.pipeline.context.PipelineContext
import com.alexsarrell.cor4al.core.pipeline.pipe.ChildPipe
import com.alexsarrell.cor4al.core.pipeline.pipe.Pipe
import com.alexsarrell.cor4al.core.pipeline.pipe.StandalonePipe
import com.alexsarrell.cor4al.core.pipeline.pipe.context.PipeContext

class GenerationFlowDefinition(
    private val context: PipelineContext,
) {
    @Suppress("UNCHECKED_CAST")
    fun process(pipe: Pipe) {
        if (pipe.pipeContext != null) {
            context.register(pipe)
        }
        when (pipe) {
            is StandalonePipe -> pipe.process()
            is ChildPipe<*> -> {
                (pipe as ChildPipe<PipeContext>).process(context.getContext(pipe.parentContext))
            }
            else -> throw IllegalArgumentException("Pipe $pipe type is not supported")
        }
    }
}
