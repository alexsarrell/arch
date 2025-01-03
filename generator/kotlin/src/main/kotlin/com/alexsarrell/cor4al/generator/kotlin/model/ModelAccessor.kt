package com.alexsarrell.cor4al.generator.kotlin.model

import com.alexsarrell.cor4al.core.model.Schema
import com.alexsarrell.cor4al.core.model.SchemaProperty
import com.alexsarrell.cor4al.core.pipeline.pipe.context.ClassSchema
import com.alexsarrell.cor4al.generator.kotlin.model.ModelAccessor.ModelProperty

data class ModelAccessor(
    val className: String,
    val parent: String?,
    val parentFields: List<ModelProperty>,
    val version: String,
    val packageName: String,
    val properties: List<ModelProperty>,
) {
    data class ModelProperty(
        val name: String,
        val required: Boolean,
        val description: String?,
        val type: String,
        val version: String,
        val defaultValue: String? = null,
    )

    constructor(schema: ClassSchema, packageName: String) :
        this(
            className = schema.first,
            parent = schema.second.parent?.className,
            version = schema.second.version,
            properties = schema.second.properties.toModelProperties(),
            parentFields = schema.second.extractParentFields().toModelProperties(),
            packageName = packageName
        )
}

private fun Schema.extractParentFields(): Map<String, SchemaProperty> {
    val fields = mutableMapOf<String, SchemaProperty>()

    fun getParentProperties(parent: Schema?) {
        if (parent == null) return
        fields += parent.properties
        getParentProperties(parent.parent?.resolvedRef)
    }

    getParentProperties(parent?.resolvedRef)
    return fields
}

private fun Map<String, SchemaProperty>.toModelProperties(): List<ModelProperty> =
    entries.map {
        ModelProperty(
            name = it.key,
            required = it.value.required,
            description = it.value.description,
            type = it.value.type,
            version = it.value.version,
            defaultValue = it.value.defaultValue,
        )
    }
