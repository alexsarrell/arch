package com.alexsarrell.cor4al.core.serializer

import com.alexsarrell.cor4al.core.SerializationContext
import com.alexsarrell.cor4al.core.model.SchemaProperty
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

class ImplementsSerializer(
    private val context: SerializationContext,
) : KSerializer<SchemaProperty> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("implements", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): SchemaProperty {
        return decoder.decodeString().let { parentClass ->
            context.specs()
                .asSequence()
                .flatMap { it.schemas.definition.values }
                .mapNotNull { it.properties[parentClass] }
                .firstOrNull()
                ?: throw ClassNotFoundException("Parent $parentClass not found")
        }
    }

    override fun serialize(encoder: Encoder, value: SchemaProperty) {
        throw NotImplementedError()
    }
}
