package com.alexsarrell.cor4al.core.util

import com.alexsarrell.cor4al.core.annotation.Metadata

fun Any.getMetadataByName(key: String): String? {
    return this::class.annotations.filterIsInstance<Metadata>().find { it.key == key }?.value
}