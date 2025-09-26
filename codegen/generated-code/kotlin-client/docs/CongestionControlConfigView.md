
# CongestionControlConfigView

## Properties
| Name | Type | Description | Notes |
| ------------ | ------------- | ------------- | ------------- |
| **allowedShardOutgoingGas** | **kotlin.Int** | How much gas the chosen allowed shard can send to a 100% congested shard.  See [&#x60;CongestionControlConfig&#x60;] for more details. |  |
| **maxCongestionIncomingGas** | **kotlin.Int** | How much gas in delayed receipts of a shard is 100% incoming congestion.  See [&#x60;CongestionControlConfig&#x60;] for more details. |  |
| **maxCongestionMemoryConsumption** | **kotlin.Int** | How much memory space of all delayed and buffered receipts in a shard is considered 100% congested.  See [&#x60;CongestionControlConfig&#x60;] for more details. |  |
| **maxCongestionMissedChunks** | **kotlin.Int** | How many missed chunks in a row in a shard is considered 100% congested. |  |
| **maxCongestionOutgoingGas** | **kotlin.Int** | How much gas in outgoing buffered receipts of a shard is 100% congested.  Outgoing congestion contributes to overall congestion, which reduces how much other shards are allowed to forward to this shard. |  |
| **maxOutgoingGas** | **kotlin.Int** | The maximum amount of gas attached to receipts a shard can forward to another shard per chunk.  See [&#x60;CongestionControlConfig&#x60;] for more details. |  |
| **maxTxGas** | **kotlin.Int** | The maximum amount of gas in a chunk spent on converting new transactions to receipts.  See [&#x60;CongestionControlConfig&#x60;] for more details. |  |
| **minOutgoingGas** | **kotlin.Int** | The minimum gas each shard can send to a shard that is not fully congested.  See [&#x60;CongestionControlConfig&#x60;] for more details. |  |
| **minTxGas** | **kotlin.Int** | The minimum amount of gas in a chunk spent on converting new transactions to receipts, as long as the receiving shard is not congested.  See [&#x60;CongestionControlConfig&#x60;] for more details. |  |
| **outgoingReceiptsBigSizeLimit** | **kotlin.Int** | Large size limit for outgoing receipts to a shard, used when it&#39;s safe to send a lot of receipts without making the state witness too large. It limits the total sum of outgoing receipts, not individual receipts. |  |
| **outgoingReceiptsUsualSizeLimit** | **kotlin.Int** | The standard size limit for outgoing receipts aimed at a single shard. This limit is pretty small to keep the size of source_receipt_proofs under control. It limits the total sum of outgoing receipts, not individual receipts. |  |
| **rejectTxCongestionThreshold** | **kotlin.Double** | How much congestion a shard can tolerate before it stops all shards from accepting new transactions with the receiver set to the congested shard. |  |



