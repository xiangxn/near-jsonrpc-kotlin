package io.near.jsonrpc.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializer(forClass = ULong::class)
object ULongSerializer : KSerializer<ULong> {
  override val descriptor: SerialDescriptor =
    PrimitiveSerialDescriptor("ULong", PrimitiveKind.STRING)

  override fun serialize(encoder: Encoder, value: ULong) {
    encoder.encodeString(value.toString())
  }

  override fun deserialize(decoder: Decoder): ULong {
    return decoder.decodeString().toULong()
  }
}
