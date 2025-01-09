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
        when (pipe) {
            is StandalonePipe -> context.run {
                pipe.run { process() }
            }
            is ChildPipe<*> -> {
                val parentContext = context.getContext(pipe.parentContext)
                context.run {
                    (pipe as ChildPipe<PipeContext>).run { process(parentContext) }
                }
            }
            else -> throw IllegalArgumentException("Pipe $pipe type is not supported")
        }
        pipe.pipeContext()?.let { context.register(pipe) }
    }
}
