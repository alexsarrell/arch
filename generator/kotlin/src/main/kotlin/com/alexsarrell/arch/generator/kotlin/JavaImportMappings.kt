package com.alexsarrell.arch.generator.kotlin

import com.alexsarrell.arch.core.model.TypeMappings
import java.time.Instant
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.util.UUID

object JavaImportMappings : TypeMappings {
    private val mappings = mutableMapOf<String, String>(
        "OffsetDateTime" to OffsetDateTime::class.javaObjectType.name,
        "LocalDateTime" to LocalDateTime::class.javaObjectType.name,
        "Instant" to Instant::class.javaObjectType.name,
        "UUID" to UUID::class.javaObjectType.name,
    )

    override fun getMappings(): Map<String, String> {
        return mappings
    }
}
