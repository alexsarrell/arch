package io.github.alexsarrell.arch.core.model

import io.github.alexsarrell.arch.core.model.type.ParentRef
import io.github.alexsarrell.arch.core.serializer.ParentRefSerializer
import kotlinx.serialization.Serializable

@Serializable
data class Schema(
    val description: String,
    val type: SchemaType,
    val properties: Map<String, SchemaProperty> = mapOf(),
    val enum: List<String> = listOf(),
    @Serializable(with = ParentRefSerializer::class)
    val parent: ParentRef? = null,
    val metadata: Map<String, String>? = null,
    val imports: List<String> = listOf(),
    val version: String,
    val exampleValue: String = "",
)
