package io.github.alexsarrell.arch.core.model

import kotlinx.serialization.Serializable

@Serializable
data class SpecSettings(
    val name: String = "",
    val title: String = "",
    val description: String = "",
)
