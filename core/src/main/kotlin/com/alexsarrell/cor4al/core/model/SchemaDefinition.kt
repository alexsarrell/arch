package com.alexsarrell.cor4al.core.model

import kotlinx.serialization.Serializable

@Serializable
data class SchemaDefinition(
    val definition: Map<String, Schema>,
)
