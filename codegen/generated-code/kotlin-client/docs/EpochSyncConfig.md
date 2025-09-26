
# EpochSyncConfig

## Properties
| Name | Type | Description | Notes |
| ------------ | ------------- | ------------- | ------------- |
| **epochSyncHorizon** | **kotlin.Int** | This serves as two purposes: (1) the node will not epoch sync and instead resort to header sync, if the genesis block is within this many blocks from the current block; (2) the node will reject an epoch sync proof if the provided proof is for an epoch that is more than this many blocks behind the current block. |  |
| **timeoutForEpochSync** | [**DurationAsStdSchemaProvider**](DurationAsStdSchemaProvider.md) | Timeout for epoch sync requests. The node will continue retrying indefinitely even if this timeout is exceeded. |  |
| **disableEpochSyncForBootstrapping** | **kotlin.Boolean** | If true, even if the node started from genesis, it will not perform epoch sync. There should be no reason to set this flag in production, because on both mainnet and testnet it would be infeasible to catch up from genesis without epoch sync. |  [optional] |
| **ignoreEpochSyncNetworkRequests** | **kotlin.Boolean** | If true, the node will ignore epoch sync requests from the network. It is strongly recommended not to set this flag, because it will prevent other nodes from bootstrapping. This flag is only included as a kill-switch and may be removed in a future release. Please note that epoch sync requests are heavily rate limited and cached, and therefore should not affect the performance of the node or introduce any non-negligible increase in network traffic. |  [optional] |



