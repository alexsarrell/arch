package com.alexsarrell.cor4al.core.model

import com.alexsarrell.cor4al.core.model.type.Version
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class Schema(
    val description: String,
    @Contextual val type: SchemaType,
    val properties: Map<String, SchemaProperty>,
    @Contextual val implements: Schema? = null,
    @Contextual val version: Version,
)
