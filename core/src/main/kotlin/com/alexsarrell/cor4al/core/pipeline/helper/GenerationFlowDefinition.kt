package com.alexsarrell.cor4al.core.pipeline.helper

import com.alexsarrell.cor4al.core.pipeline.context.GenerationContext
import com.alexsarrell.cor4al.core.pipeline.pipe.ChildPipe
import com.alexsarrell.cor4al.core.pipeline.pipe.Pipe
import com.alexsarrell.cor4al.core.pipeline.pipe.StandalonePipe
import com.alexsarrell.cor4al.core.pipeline.pipe.context.PipeContext

class GenerationFlowDefinition(private val context: GenerationContext) {

    fun process(pipe: Pipe) {
        context.register(pipe)
        when (pipe) {
            is StandalonePipe -> pipe.process()
            is ChildPipe<*> -> {
                (pipe as? ChildPipe<PipeContext>)?.process(context.getContext(pipe.parentContext))
            }
        }
    }

    fun process(pipe: Pipe, condition: () -> Boolean) {
        if (condition()) process(pipe)
    }
}