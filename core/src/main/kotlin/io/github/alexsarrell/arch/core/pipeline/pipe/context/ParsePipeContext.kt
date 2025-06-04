package io.github.alexsarrell.arch.core.pipeline.pipe.context

import io.github.alexsarrell.arch.core.model.Schema
import io.github.alexsarrell.arch.core.model.Spec
import io.github.alexsarrell.arch.core.pipeline.context.ContextProperty
import io.github.alexsarrell.arch.core.pipeline.context.PipelineContext

class ParsePipeContext(context: PipelineContext): PipeContext(context) {
    fun addSpec(spec: Spec) {
        specs.add(spec)
    }
}

var ParsePipeContext.specs: MutableSet<Spec> by ContextProperty("specs") { mutableSetOf() }

typealias ClassSchema = Map.Entry<String, Schema>