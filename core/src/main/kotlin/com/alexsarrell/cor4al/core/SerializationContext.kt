package com.alexsarrell.cor4al.core

import com.alexsarrell.cor4al.core.model.Spec

interface SerializationContext {

    fun specs(): List<Spec>

    fun load(spec: Spec): Map<String, Spec>

    operator fun get(name: String): Spec?
}
