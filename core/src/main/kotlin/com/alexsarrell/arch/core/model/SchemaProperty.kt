package com.alexsarrell.arch.core.model

import kotlinx.serialization.Serializable

@Serializable
data class SchemaProperty(
    val required: Boolean,
    val description: String?,
    val type: String,
    val version: String,
    val defaultValue: String? = null,
)
