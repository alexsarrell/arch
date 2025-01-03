package com.alexsarrell.cor4al.generator.kotlin

object JavaTypeMappings {
    private val mappings = mutableMapOf<String, String>(
        "string" to String::class.java.name,
        "int" to Int::class.javaObjectType.name,
        "float" to Float::class.javaObjectType.name,
        "boolean" to Boolean::class.javaObjectType.name,
    )

    fun getMappings(): Map<String, String> {
        return mappings
    }
}
