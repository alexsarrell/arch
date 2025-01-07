package com.alexsarrell.arch.core.model

import kotlinx.serialization.Serializable

@Serializable
data class SpecSettings(
    val name: String,
    val summary: String = "",
    val description: String = "",
)
