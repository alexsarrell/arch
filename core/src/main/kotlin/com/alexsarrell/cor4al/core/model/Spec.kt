package com.alexsarrell.cor4al.core.model

import kotlinx.serialization.Serializable

@Serializable
data class Spec(
    val settings: SchemaSettings,
    val schemas: Map<String, Schema>,
)
