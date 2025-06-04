package io.github.alexsarrell.arch.core.util

import io.github.alexsarrell.arch.core.annotation.Metadata
import io.github.alexsarrell.arch.core.model.accessor.ObjectTemplateData.MetadataEntry

fun Any.getMetadataByName(key: String): String? {
    return this::class.annotations.filterIsInstance<Metadata>().find { it.key == key }?.value
}

fun Map<String, String>.toMetadataEntry() = map { MetadataEntry(it.key, it.value) }
