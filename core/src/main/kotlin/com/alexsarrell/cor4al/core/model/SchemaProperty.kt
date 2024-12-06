package com.alexsarrell.cor4al.core.model

import com.alexsarrell.cor4al.core.model.type.Version
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class SchemaProperty(
    val required: Boolean,
    val description: String?,
    @Contextual val type: String,
    @Contextual val version: Version,
    @Contextual val defaultValue: String? = null,
)
