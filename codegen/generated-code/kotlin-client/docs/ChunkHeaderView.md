
# ChunkHeaderView

## Properties
| Name | Type | Description | Notes |
| ------------ | ------------- | ------------- | ------------- |
| **balanceBurnt** | **kotlin.String** |  |  |
| **chunkHash** | **kotlin.String** |  |  |
| **encodedLength** | **kotlin.Int** |  |  |
| **encodedMerkleRoot** | **kotlin.String** |  |  |
| **gasLimit** | **kotlin.Int** |  |  |
| **gasUsed** | **kotlin.Int** |  |  |
| **heightCreated** | **kotlin.Int** |  |  |
| **heightIncluded** | **kotlin.Int** |  |  |
| **outcomeRoot** | **kotlin.String** |  |  |
| **outgoingReceiptsRoot** | **kotlin.String** |  |  |
| **prevBlockHash** | **kotlin.String** |  |  |
| **prevStateRoot** | **kotlin.String** |  |  |
| **rentPaid** | **kotlin.String** | TODO(2271): deprecated. |  |
| **shardId** | **kotlin.Int** | The shard identifier. It may be an arbitrary number - it does not need to be a number in the range 0..NUM_SHARDS. The shard ids do not need to be sequential or contiguous.  The shard id is wrapped in a new type to prevent the old pattern of using indices in range 0..NUM_SHARDS and casting to ShardId. Once the transition if fully complete it potentially may be simplified to a regular type alias. |  |
| **signature** | **kotlin.String** |  |  |
| **txRoot** | **kotlin.String** |  |  |
| **validatorProposals** | [**kotlin.collections.List&lt;ValidatorStakeView&gt;**](ValidatorStakeView.md) |  |  |
| **validatorReward** | **kotlin.String** | TODO(2271): deprecated. |  |
| **bandwidthRequests** | [**BandwidthRequests**](BandwidthRequests.md) |  |  [optional] |
| **congestionInfo** | [**CongestionInfoView**](CongestionInfoView.md) |  |  [optional] |



