
# ShardLayoutV1

## Properties
| Name | Type | Description | Notes |
| ------------ | ------------- | ------------- | ------------- |
| **boundaryAccounts** | **kotlin.collections.List&lt;kotlin.String&gt;** | The boundary accounts are the accounts on boundaries between shards. Each shard contains a range of accounts from one boundary account to another - or the smallest or largest account possible. The total number of shards is equal to the number of boundary accounts plus 1. |  |
| **version** | **kotlin.Int** | Version of the shard layout, this is useful for uniquely identify the shard layout |  |
| **shardsSplitMap** | **kotlin.collections.List&lt;kotlin.collections.List&lt;kotlin.Int&gt;&gt;** | Maps shards from the last shard layout to shards that it splits to in this shard layout, Useful for constructing states for the shards. None for the genesis shard layout |  [optional] |
| **toParentShardMap** | **kotlin.collections.List&lt;kotlin.Int&gt;** | Maps shard in this shard layout to their parent shard Since shard_ids always range from 0 to num_shards - 1, we use vec instead of a hashmap |  [optional] |



