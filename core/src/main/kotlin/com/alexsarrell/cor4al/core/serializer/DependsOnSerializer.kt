package com.alexsarrell.cor4al.core.serializer

import com.alexsarrell.cor4al.core.SerializationContext
import com.alexsarrell.cor4al.core.exception.SpecNotFoundException
import com.alexsarrell.cor4al.core.model.Spec
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

class DependsOnSerializer(
    private val serializationContext: SerializationContext,
) : KSerializer<Spec> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("dependsOn", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): Spec {
        return decoder.decodeString().let { parentSpecName ->
            serializationContext[parentSpecName] ?: throw SpecNotFoundException(parentSpecName)
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    override fun serialize(encoder: Encoder, value: Spec) {
        return value.settings.dependsOn?.let { encoder.encodeString(it) } ?: encoder.encodeNull()
    }
}
