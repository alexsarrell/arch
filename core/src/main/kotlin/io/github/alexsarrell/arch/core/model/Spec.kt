package io.github.alexsarrell.arch.core.model

import kotlinx.serialization.Serializable

@Serializable
data class Spec(
    val settings: SpecSettings = SpecSettings(),
    val schemas: Map<String, Schema>,
) {
    fun getFileName(): String {
        return settings.title.ifEmpty { settings.name }
    }
}