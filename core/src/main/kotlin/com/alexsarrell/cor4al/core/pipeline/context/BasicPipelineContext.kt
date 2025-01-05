package com.alexsarrell.cor4al.core.pipeline.context

import com.alexsarrell.cor4al.core.pipeline.pipe.Pipe
import com.alexsarrell.cor4al.core.pipeline.pipe.context.PipeContext

open class BasicPipelineContext : PipelineContext {

    override val context: MutableMap<String, Any?> = mutableMapOf()

    private val pipes: MutableMap<String, Pipe> = mutableMapOf()

    override fun <P : Pipe> register(pipe: P) {
        val pipeContext =
            requireNotNull(pipe.pipeContext) { "Context is not declared for $pipe" }
        pipes[pipeContext::class.java.name] = pipe
    }

    override fun <C : PipeContext> getContext(contextClass: Class<C>): C {
        return requireNotNull(pipes[contextClass.name]) {
            "Context for class: $contextClass not found"
        }.pipeContext as C
    }

    operator fun set(key: String, value: Any?) {
        context[key] = value
    }

    operator fun get(key: String): Any? {
        return context[key]
    }
}
