package io.github.alexsarrell.arch.core.model

import kotlinx.serialization.Serializable

@Serializable
data class SchemaProperty(
    val required: Boolean,
    val description: String?,
    val type: String,
    val version: String,
    val exampleValue: String? = null,
    val defaultValue: String? = null,
)
