package com.alexsarrell.cor4al.generator.kotlin

import com.alexsarrell.cor4al.core.model.TypeMappings
import java.time.Instant
import java.time.LocalDateTime
import java.time.OffsetDateTime

object JavaTypeMappings : TypeMappings {
    private val mappings = mutableMapOf<String, String>(
        "OffsetDateTime" to OffsetDateTime::class.javaObjectType.name,
        "LocalDateTime" to LocalDateTime::class.javaObjectType.name,
        "Instant" to Instant::class.javaObjectType.name,
    )

    override fun getMappings(): Map<String, String> {
        return mappings
    }
}
