package com.alexsarrell.arch.core.model

enum class DocType(val templateName: String, val extension: String) {
    MARKDOWN("markdown", ".md"),
    HTML("html", ".html")
}