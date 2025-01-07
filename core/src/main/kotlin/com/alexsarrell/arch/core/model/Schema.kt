package com.alexsarrell.arch.core.model

import com.alexsarrell.arch.core.model.type.ParentRef
import com.alexsarrell.arch.core.serializer.ParentRefSerializer
import kotlinx.serialization.Serializable

@Serializable
data class Schema(
    val description: String,
    val type: SchemaType,
    val properties: Map<String, SchemaProperty>,
    @Serializable(with = ParentRefSerializer::class)
    val parent: ParentRef? = null,
    val metadata: Map<String, String>? = null,
    val imports: List<String> = listOf(),
    val version: String,
    val exampleValue: String = "",
)
