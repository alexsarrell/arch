package io.github.alexsarrell.arch.core.util

import io.github.alexsarrell.arch.core.annotation.Metadata

fun Any.getMetadataByName(key: String): String? {
    return this::class.annotations.filterIsInstance<Metadata>().find { it.key == key }?.value
}