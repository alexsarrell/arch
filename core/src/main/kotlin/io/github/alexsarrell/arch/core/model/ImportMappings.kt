package io.github.alexsarrell.arch.core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ImportMappings(
    @SerialName("import-mappings") val importMappings: Map<String, String>,
)
