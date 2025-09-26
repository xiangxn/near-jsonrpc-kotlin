package io.near.jsonrpc.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

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

object ULongListSerializer : KSerializer<List<ULong>> {
  private val delegate = ListSerializer(ULongSerializer)
  override val descriptor: SerialDescriptor = delegate.descriptor

  override fun serialize(encoder: Encoder, value: List<ULong>) = delegate.serialize(encoder, value)

  override fun deserialize(decoder: Decoder): List<ULong> = delegate.deserialize(decoder)
}

object UIntSerializer : KSerializer<UInt> {
  override val descriptor: SerialDescriptor =
    PrimitiveSerialDescriptor("UInt", PrimitiveKind.STRING)

  override fun serialize(encoder: Encoder, value: UInt) {
    encoder.encodeString(value.toString())
  }

  override fun deserialize(decoder: Decoder): UInt {
    return decoder.decodeString().toUInt()
  }
}

object UIntListSerializer : KSerializer<List<UInt>> {
  private val delegate = ListSerializer(UIntSerializer)
  override val descriptor: SerialDescriptor = delegate.descriptor

  override fun serialize(encoder: Encoder, value: List<UInt>) = delegate.serialize(encoder, value)

  override fun deserialize(decoder: Decoder): List<UInt> = delegate.deserialize(decoder)
}
