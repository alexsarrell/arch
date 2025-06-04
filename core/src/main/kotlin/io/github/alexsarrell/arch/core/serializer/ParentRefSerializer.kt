package io.github.alexsarrell.arch.core.serializer

import io.github.alexsarrell.arch.core.model.type.ParentRef
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

class ParentRefSerializer : KSerializer<ParentRef> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("parent", PrimitiveKind.STRING)

    private val refPattern = "(\\.{1,2}/?.*#/.*|#/.*)".toRegex()

    override fun deserialize(decoder: Decoder): ParentRef {
        val ref = decoder.decodeString()
        if (!refPattern.matches(ref)) {
            throw IllegalArgumentException("Parent reference $ref has an illegal format")
        }
        return ParentRef(ref)
    }

    override fun serialize(encoder: Encoder, value: ParentRef) {
        throw NotImplementedError()
    }
}