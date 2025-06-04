package io.github.alexsarrell.arch.core.pipeline.context

import kotlin.reflect.KProperty

class ContextProperty<T : Any?>(
    private val key: String,
    private val defaultValue: (() -> T)? = null
) {
    operator fun getValue(context: ContextHolder, property: KProperty<*>): T {
        return if (context.context.containsKey(key)) {
            context.context[key] as T
        } else if (defaultValue != null) {
            defaultValue.invoke().also { context.context[key] = it }
        } else {
            throw IllegalStateException("Property $key is not set")
        }
    }

    operator fun setValue(context: ContextHolder, property: KProperty<*>, value: T) {
        context.context[key] = value
    }
}