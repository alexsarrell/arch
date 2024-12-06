package com.alexsarrell.cor4al.core.model

import kotlinx.serialization.Serializable

@Serializable
data class SettingsDefinition(
    val dependsOn: String? = null,
    val name: String,
)
