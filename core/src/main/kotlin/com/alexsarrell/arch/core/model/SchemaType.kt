package com.alexsarrell.arch.core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class SchemaType {
    @SerialName("data") DATA,
    @SerialName("abstract") ABSTRACT,
    @SerialName("open") OPEN,
    @SerialName("enum") ENUM,
}
