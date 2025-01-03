package com.alexsarrell.cor4al.core.model

enum class SupportedFileType(vararg val extension: String) {
    YAML("yaml", "yml"),
    JSON("json"),
    XML("xml"),

    ALL("yaml", "yml", "json", "xml");

/*
    *(mutableListOf<String>().apply {
        SupportedFileType.entries.filter { it != ALL }.map { plus(it.extension) }
    }.toTypedArray())
*/

    val pattern = ".*\\.(${extension.joinToString("|")})".toRegex()
}
