package io.github.alexsarrell.arch.core.model

enum class SupportedFileType(vararg val extension: String) {
    YAML("yaml", "yml"),
    JSON("json"),
    XML("xml"),

    ALL("yaml", "yml", "json", "xml");

    val pattern = ".*\\.(${extension.joinToString("|")})".toRegex()
}
