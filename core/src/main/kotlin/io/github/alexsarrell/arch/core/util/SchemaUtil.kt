package io.github.alexsarrell.arch.core.util

import io.github.alexsarrell.arch.core.model.Schema
import io.github.alexsarrell.arch.core.model.SchemaProperty
import io.github.alexsarrell.arch.core.model.SchemaType
import io.github.alexsarrell.arch.core.model.TaskContext
import io.github.alexsarrell.arch.core.model.accessor.ObjectTemplateData.ObjectProperty
import io.github.alexsarrell.arch.core.pipeline.pipe.context.ParsePipeContext
import io.github.alexsarrell.arch.core.pipeline.pipe.context.importMappings

fun Schema.extractParentFields(): Map<String, SchemaProperty> {
    val fields = mutableMapOf<String, SchemaProperty>()

    fun getParentProperties(parent: Schema?) {
        if (parent == null) return
        fields += parent.properties
        getParentProperties(parent.parent?.resolvedRef)
    }

    getParentProperties(parent?.resolvedRef)
    return fields
}

fun Schema.buildImports(context: TaskContext): List<String> {
    val importMappings = context.importMappings + context.getContext(ParsePipeContext::class.java).importMappings
    val propertiesImports = properties.values.mapNotNull { importMappings[it.type] }
    val parentPropertiesImports = extractParentFields().values.mapNotNull { importMappings[it.type] }

    return propertiesImports.plus(parentPropertiesImports).plus(imports)
}

fun Schema.isOpen(): Boolean =
    when (type) {
        SchemaType.ABSTRACT, SchemaType.OPEN -> true
        else -> false
    }

fun Map<String, SchemaProperty>.toModelProperties(): List<ObjectProperty> =
    entries.map {
        ObjectProperty(
            name = it.key,
            required = it.value.required,
            description = it.value.description,
            type = it.value.type,
            version = it.value.version,
            defaultValue = it.value.defaultValue,
        )
    }
