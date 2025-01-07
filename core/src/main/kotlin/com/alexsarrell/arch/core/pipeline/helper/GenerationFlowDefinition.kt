package com.alexsarrell.arch.core.pipeline.helper

import com.alexsarrell.arch.core.pipeline.context.PipelineContext
import com.alexsarrell.arch.core.pipeline.pipe.ChildPipe
import com.alexsarrell.arch.core.pipeline.pipe.Pipe
import com.alexsarrell.arch.core.pipeline.pipe.StandalonePipe
import com.alexsarrell.arch.core.pipeline.pipe.context.PipeContext

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
                val parentContext = context.getContext(pipe.parentContext)
                (pipe as ChildPipe<PipeContext>).process(parentContext)
            }
            else -> throw IllegalArgumentException("Pipe $pipe type is not supported")
        }
    }
}
