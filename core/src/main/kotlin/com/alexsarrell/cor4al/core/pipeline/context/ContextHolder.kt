package com.alexsarrell.cor4al.core.pipeline.context

interface ContextHolder {
    val context: MutableMap<String, Any?>
}

abstract class BasicContextHolder : ContextHolder {
    override val context: MutableMap<String, Any?> = mutableMapOf()
}