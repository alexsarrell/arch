package com.alexsarrell.cor4al.core.pipeline.helper

import com.alexsarrell.cor4al.core.pipeline.Cor4alContext
import com.alexsarrell.cor4al.core.pipeline.pipe.Pipe

class GenerationFlowDefinition(private val context: Cor4alContext) {

    fun process(pipe: Pipe) {
        context.register(pipe)
        pipe.process()
    }

    fun process(pipe: Pipe, condition: () -> Boolean) {
        if (condition()) process(pipe)
    }
}