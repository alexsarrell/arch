package com.alexsarrell.cor4al.generator

import com.alexsarrell.cor4al.core.SerializationContext
import com.alexsarrell.cor4al.core.model.Spec

class KotlinSerializationContext : SerializationContext {
    private val context: MutableMap<String, Spec> = mutableMapOf()

    override fun load(spec: Spec): Map<String, Spec> {
        context[spec.settings.name] = spec
        return context
    }

    override operator fun get(name: String): Spec? {
        return context[name]
    }

    override fun specs(): List<Spec> {
        return context.values.toList()
    }
}
