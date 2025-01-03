package com.alexsarrell.cor4al.generator.kotlin.parser

import com.alexsarrell.cor4al.core.parser.AbstractYamlParser
import com.alexsarrell.cor4al.core.pipeline.pipe.context.ParsePipeContext

class KotlinYamlParser(
    serializationContext: ParsePipeContext,
) : AbstractYamlParser(serializationContext) {

    override fun validateMappings(typeMappings: Map<String, String>) {
        typeMappings.values.forEach { Class.forName(it) }
    }
}
