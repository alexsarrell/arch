package com.alexsarrell.cor4al.core.pipeline.pipe.context

import com.alexsarrell.cor4al.core.model.Schema
import com.alexsarrell.cor4al.core.model.Spec
import com.alexsarrell.cor4al.core.pipeline.context.GenerationContext

class ParsePipeContext(context: GenerationContext): PipeContext(context) {
    val specs: MutableSet<Spec> = mutableSetOf()

    fun addSpec(spec: Spec) {
        specs.add(spec)
    }
}

typealias ClassSchema = Pair<String, Schema>