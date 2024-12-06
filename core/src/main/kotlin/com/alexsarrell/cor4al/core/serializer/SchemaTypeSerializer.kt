package com.alexsarrell.cor4al.core.serializer

import com.alexsarrell.cor4al.core.model.SchemaType
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

class SchemaTypeSerializer : KSerializer<SchemaType> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("type", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): SchemaType {
        val type = decoder.decodeString()
        val mapping = SchemaType.entries.first { it.typeAlias == type }
        return mapping
    }

    override fun serialize(encoder: Encoder, value: SchemaType) {
        return encoder.encodeString(value.typeAlias)
    }
}
