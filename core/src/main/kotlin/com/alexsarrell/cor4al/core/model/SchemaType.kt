package com.alexsarrell.cor4al.core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class SchemaType {
    @SerialName("data") DATA,
    @SerialName("abstract") ABSTRACT,
    @SerialName("class") CLASS,
}
