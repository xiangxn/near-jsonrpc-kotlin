
# SyncConcurrency

## Properties
| Name | Type | Description | Notes |
| ------------ | ------------- | ------------- | ------------- |
| **apply** | **kotlin.Int** | Maximum number of \&quot;apply parts\&quot; tasks that can be performed in parallel. This is a very disk-heavy task and therefore we set this to a low limit, or else the rocksdb contention makes the whole server freeze up. |  |
| **applyDuringCatchup** | **kotlin.Int** | Maximum number of \&quot;apply parts\&quot; tasks that can be performed in parallel during catchup. We set this to a very low value to avoid overloading the node while it is still performing normal tasks. |  |
| **peerDownloads** | **kotlin.Int** | Maximum number of outstanding requests for decentralized state sync. |  |
| **perShard** | **kotlin.Int** | The maximum parallelism to use per shard. This is mostly for fairness, because the actual rate limiting is done by the TaskTrackers, but this is useful for balancing the shards a little. |  |



