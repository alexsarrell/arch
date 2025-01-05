package com.alexsarrell.cor4al.core.pipeline.pipe.context

import com.alexsarrell.cor4al.core.model.Schema
import com.alexsarrell.cor4al.core.model.Spec
import com.alexsarrell.cor4al.core.pipeline.context.ContextProperty
import com.alexsarrell.cor4al.core.pipeline.context.PipelineContext

class ParsePipeContext(context: PipelineContext): PipeContext(context) {
    fun addSpec(spec: Spec) {
        specs.add(spec)
    }
}

var ParsePipeContext.specs: MutableSet<Spec> by ContextProperty("specs") { mutableSetOf() }

typealias ClassSchema = Pair<String, Schema>