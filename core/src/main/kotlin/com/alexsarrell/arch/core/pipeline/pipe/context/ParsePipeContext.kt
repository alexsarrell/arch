package com.alexsarrell.arch.core.pipeline.pipe.context

import com.alexsarrell.arch.core.model.Schema
import com.alexsarrell.arch.core.model.Spec
import com.alexsarrell.arch.core.pipeline.context.ContextProperty
import com.alexsarrell.arch.core.pipeline.context.PipelineContext

class ParsePipeContext(context: PipelineContext): PipeContext(context) {
    fun addSpec(spec: Spec) {
        specs.add(spec)
    }
}

var ParsePipeContext.specs: MutableSet<Spec> by ContextProperty("specs") { mutableSetOf() }

typealias ClassSchema = Pair<String, Schema>