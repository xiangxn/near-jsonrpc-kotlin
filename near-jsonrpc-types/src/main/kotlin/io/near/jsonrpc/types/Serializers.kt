package io.near.jsonrpc.types

import java.math.BigInteger
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object BigIntegerAsStringSerializer : KSerializer<BigInteger> {
  override val descriptor: SerialDescriptor =
    PrimitiveSerialDescriptor("BigInteger", PrimitiveKind.STRING)

  override fun deserialize(decoder: Decoder): BigInteger = BigInteger(decoder.decodeString())

  override fun serialize(encoder: Encoder, value: BigInteger) =
    encoder.encodeString(value.toString())
}

object ULongAsStringSerializer : KSerializer<ULong> {
  override val descriptor: SerialDescriptor =
    PrimitiveSerialDescriptor("ULong", PrimitiveKind.STRING)

  override fun deserialize(decoder: Decoder): ULong = decoder.decodeString().toULong()

  override fun serialize(encoder: Encoder, value: ULong) = encoder.encodeString(value.toString())
}
