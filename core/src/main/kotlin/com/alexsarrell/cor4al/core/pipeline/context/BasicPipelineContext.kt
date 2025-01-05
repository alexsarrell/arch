package com.alexsarrell.cor4al.core.pipeline.context

import com.alexsarrell.cor4al.core.pipeline.pipe.Pipe
import com.alexsarrell.cor4al.core.pipeline.pipe.context.PipeContext

open class BasicPipelineContext : PipelineContext {

    override val context: MutableMap<String, Any?> = mutableMapOf()

    private val pipes: MutableList<Pipe> = mutableListOf()

    override fun <P : Pipe> register(pipe: P) {
        pipes.add(pipe)
    }

    override fun <C : PipeContext> getContext(contextClass: Class<C>): C {
        val context =
            pipes.find { it.pipeContext::class.java.isAssignableFrom(contextClass) }
        return requireNotNull(context) {
            "Context for pipe: $contextClass not found"
        }.pipeContext as C
    }

    operator fun set(key: String, value: Any?) {
        context[key] = value
    }

    operator fun get(key: String): Any? {
        return context[key]
    }
}

var PipelineContext.outputDir: String by ContextProperty("outputDir")

var PipelineContext.packageName: String by ContextProperty("packageName")

var PipelineContext.parentPackage: String by ContextProperty("parentPackage")

var PipelineContext.templateDir: String? by ContextProperty("templateDir")

var PipelineContext.typeMappings: Map<String, String> by ContextProperty("typeMappings")

var PipelineContext.specSource: String by ContextProperty("specSource")

var PipelineContext.specLimit: List<String> by ContextProperty("specLimit")

var PipelineContext.generatorFileExtension: String by ContextProperty("generatorFileExtension")