package io.github.alexsarrell.arch.core.pipeline.pipe.context

import io.github.alexsarrell.arch.core.model.Schema
import io.github.alexsarrell.arch.core.model.Spec
import io.github.alexsarrell.arch.core.model.TaskContext
import io.github.alexsarrell.arch.core.pipeline.context.ContextProperty

class ParsePipeContext(context: TaskContext): PipeContext(context) {
    fun addSpec(spec: Spec) {
        specs.add(spec)
    }
}

var ParsePipeContext.specs: MutableSet<Spec> by ContextProperty("specs") { mutableSetOf() }
var ParsePipeContext.importMappings: Map<String, String> by ContextProperty("importMappings")

typealias ClassSchema = Map.Entry<String, Schema>
