package com.alexsarrell.cor4al.core.serializer

import com.alexsarrell.cor4al.core.exception.MappingNotFoundException
import com.alexsarrell.cor4al.core.model.type.PropertyType
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

class PropertyTypeSerializer(
    private val typeMappings: Map<String, String>,
) : KSerializer<PropertyType> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("type", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): PropertyType {
        val type = decoder.decodeString()
        val mapping = typeMappings[type] ?: throw MappingNotFoundException(type)
        return PropertyType(mapping)
    }

    override fun serialize(encoder: Encoder, value: PropertyType) {
        TODO("Not yet implemented")
    }
}
