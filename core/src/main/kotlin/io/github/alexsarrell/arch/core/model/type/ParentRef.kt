package io.github.alexsarrell.arch.core.model.type

import io.github.alexsarrell.arch.core.model.Schema
import io.github.alexsarrell.arch.core.model.Spec
import java.io.File

data class ParentRef(val fullRef: String) {
    private val fileRef: String? = if (EXTERNAL_FILE_REF_PATTERN.matches(fullRef)) {
        fullRef.substringBefore("#")
    } else {
        null
    }
    private val schemaRef: String = fullRef.substringAfter("#")
    var resolvedRef: Schema? = null
    val className: String = schemaRef.substringAfterLast("/")

    fun resolveParentFile(file: File): File {
        return if (fileRef != null) {
            file.resolveSibling(fileRef)
        } else {
            file
        }
    }

    fun resolveRef(spec: Spec) {
        val schemaRef = schemaRef.substringAfterLast("/")
        resolvedRef = spec.schemas[schemaRef] ?: throw NoSuchElementException("Schema not found by reference: $fullRef")
    }

    companion object {
        val EXTERNAL_FILE_REF_PATTERN: Regex = "^(\\.\\.?|[^#].*)/.*#/(.*/)*.*".toRegex()
    }
}
