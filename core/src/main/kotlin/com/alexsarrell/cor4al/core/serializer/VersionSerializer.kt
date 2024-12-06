package com.alexsarrell.cor4al.core.serializer

import com.alexsarrell.cor4al.core.model.type.Version
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

class VersionSerializer : KSerializer<Version> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("version", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): Version {
        val value = decoder.decodeString().split("\\.")

        val major = value[0].toInt()
        val minor = value[1].toInt()
        val patch = value[2].toInt()

        return Version(major, minor, patch)
    }

    override fun serialize(encoder: Encoder, value: Version) {
        encoder.encodeString(value.toString())
    }
}
