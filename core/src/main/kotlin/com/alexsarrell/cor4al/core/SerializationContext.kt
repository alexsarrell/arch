package com.alexsarrell.cor4al.core

import com.alexsarrell.cor4al.core.model.Spec
import java.io.File

interface SerializationContext {

    fun load(spec: Spec): Map<String, Spec>

    fun loadTemplates(templates: List<File>)
}
