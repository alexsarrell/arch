package com.alexsarrell.arch.core.model

import kotlinx.serialization.Serializable

@Serializable
data class Spec(
    val settings: SpecSettings,
    val schemas: Map<String, Schema>,
)
