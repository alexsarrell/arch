package com.alexsarrell.cor4al.generator.kotlin.parser

import com.alexsarrell.cor4al.core.AbstractYamlParser
import com.alexsarrell.cor4al.core.SerializationContext
import com.alexsarrell.cor4al.generator.kotlin.JavaTypeMappings
import com.alexsarrell.cor4al.generator.kotlin.KotlinSchemaPropertyValidator

class KotlinYamlParser(
    serializationContext: SerializationContext,
    typeMappings: Map<String, String>,
    validator: KotlinSchemaPropertyValidator,
    strictMode: Boolean = false,
) : AbstractYamlParser(serializationContext, typeMappings, validator, strictMode) {

    override val basicTypeMappings: Map<String, String> =
        JavaTypeMappings.getMappings()

    override fun validateMappings(typeMappings: Map<String, String>) {
        typeMappings.values.forEach { Class.forName(it) }
    }
}
