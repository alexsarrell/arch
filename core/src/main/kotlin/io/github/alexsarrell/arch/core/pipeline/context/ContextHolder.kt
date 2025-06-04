package io.github.alexsarrell.arch.core.pipeline.context

interface ContextHolder {
    val context: MutableMap<String, Any?>
}
