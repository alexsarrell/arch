package com.alexsarrell.cor4al.core.serializer

import com.alexsarrell.cor4al.core.model.type.PropertyValue
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

class PropertyValueSerializer : KSerializer<PropertyValue> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("defaultValue", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): PropertyValue {
        val value = decoder.decodeString()

        return PropertyValue(value)
    }

    override fun serialize(encoder: Encoder, value: PropertyValue) {
        return encoder.encodeString(value.specValue)
    }
}
