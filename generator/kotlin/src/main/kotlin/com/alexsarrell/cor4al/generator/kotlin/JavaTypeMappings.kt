package com.alexsarrell.cor4al.generator.kotlin

object JavaTypeMappings {
    private val mappings = mutableMapOf<String, String>(
        "string" to String::class.java.name,
        "int" to Int::class.java.name,
        "float" to Float::class.java.name,
        "boolean" to Boolean::class.java.name,
    )

    fun getMappings(): Map<String, String> {
        return mappings
    }
}
