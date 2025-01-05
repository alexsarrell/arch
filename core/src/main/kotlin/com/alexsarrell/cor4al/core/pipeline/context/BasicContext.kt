package com.alexsarrell.cor4al.core.pipeline.context

import com.alexsarrell.cor4al.core.pipeline.pipe.Pipe
import com.alexsarrell.cor4al.core.pipeline.pipe.context.PipeContext
import kotlin.reflect.KProperty

open class BasicContext : GenerationContext {

    override val context: MutableMap<String, Any?> = mutableMapOf()

    private val contexts: MutableMap<String, PipeContext> = mutableMapOf()

    override fun contexts(): Map<String, PipeContext> = contexts

    override fun <P : Pipe> register(pipe: P) {
        contexts[pipe.pipeContext::class.java.name] = pipe.pipeContext
    }

    override fun <C : PipeContext> getContext(contextClass: Class<C>): C {
        return requireNotNull(contexts[contextClass.name]) {
            "Context for pipe: $contextClass not found"
        } as C
    }

    operator fun set(key: String, value: Any?) {
        context[key] = value
    }

    operator fun get(key: String): Any? {
        return context[key]
    }
}

class ContextProperty<T : Any>(
    private val key: String,
    private val defaultValue: () -> T? = { null },
) {

    operator fun getValue(context: GenerationContext, property: KProperty<*>): T? {
        return if (context.context.containsKey(key)) {
            context.context[key] as T?
        } else {
            defaultValue().also {
                context.context[key] = it
            }
        }
    }

    operator fun setValue(context: GenerationContext, property: KProperty<*>, value: T?) {
        context.context[key] = value
    }
}

var GenerationContext.outputDir: String? by ContextProperty("outputDir")

var GenerationContext.packageName: String? by ContextProperty("packageName")

var GenerationContext.parentPackage: String? by ContextProperty("parentPackage")

var GenerationContext.templateDir: String? by ContextProperty("templateDir")

var GenerationContext.typeMappings: Map<String, String>? by ContextProperty("typeMappings")
