package com.alexsarrell.cor4al.core.pipeline

import com.alexsarrell.cor4al.core.pipeline.pipe.Pipe
import com.alexsarrell.cor4al.core.pipeline.pipe.context.PipeContext

open class BasicContext : Cor4alContext {
    private val contexts: MutableMap<String, PipeContext> = mutableMapOf()

    override fun contexts(): Map<String, PipeContext> = contexts

    override fun <P : Pipe> register(pipe: P) {
        contexts[pipe.pipeContext::class.java.name] = pipe.pipeContext
    }

    override fun <P : PipeContext> getContext(pipeClass: Class<P>): P {
        return requireNotNull(contexts[pipeClass.name]) {
            "Context for pipe: $pipeClass not found"
        } as P
    }
}