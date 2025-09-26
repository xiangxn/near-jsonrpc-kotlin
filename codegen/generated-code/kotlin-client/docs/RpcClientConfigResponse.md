
# RpcClientConfigResponse

## Properties
| Name | Type | Description | Notes |
| ------------ | ------------- | ------------- | ------------- |
| **archive** | **kotlin.Boolean** | Not clear old data, set &#x60;true&#x60; for archive nodes. |  |
| **blockFetchHorizon** | **kotlin.Int** | Horizon at which instead of fetching block, fetch full state. |  |
| **blockHeaderFetchHorizon** | **kotlin.Int** | Behind this horizon header fetch kicks in. |  |
| **blockProductionTrackingDelay** | **kotlin.collections.List&lt;kotlin.Int&gt;** | Duration to check for producing / skipping block. |  |
| **catchupStepPeriod** | **kotlin.collections.List&lt;kotlin.Int&gt;** | Time between check to perform catchup. |  |
| **chainId** | **kotlin.String** | Chain id for status. |  |
| **chunkRequestRetryPeriod** | **kotlin.collections.List&lt;kotlin.Int&gt;** | Time between checking to re-request chunks. |  |
| **chunkValidationThreads** | **kotlin.Int** | Number of threads for ChunkValidationActor pool. |  |
| **chunkWaitMult** | **kotlin.collections.List&lt;kotlin.Int&gt;** | Multiplier for the wait time for all chunks to be received. |  |
| **clientBackgroundMigrationThreads** | **kotlin.Int** | Number of threads to execute background migration work in client. |  |
| **doomslugStepPeriod** | **kotlin.collections.List&lt;kotlin.Int&gt;** | Time between running doomslug timer. |  |
| **enableMultilineLogging** | **kotlin.Boolean** |  |  |
| **enableStatisticsExport** | **kotlin.Boolean** | Re-export storage layer statistics as prometheus metrics. |  |
| **epochLength** | **kotlin.Int** | Epoch length. |  |
| **epochSync** | [**EpochSyncConfig**](EpochSyncConfig.md) | Options for epoch sync. |  |
| **expectedShutdown** | **kotlin.String** | Graceful shutdown at expected block height. |  |
| **gc** | [**GCConfig**](GCConfig.md) | Garbage collection configuration. |  |
| **headerSyncExpectedHeightPerSecond** | **kotlin.Int** | Expected increase of header head height per second during header sync |  |
| **headerSyncInitialTimeout** | **kotlin.collections.List&lt;kotlin.Int&gt;** | How much time to wait after initial header sync |  |
| **headerSyncProgressTimeout** | **kotlin.collections.List&lt;kotlin.Int&gt;** | How much time to wait after some progress is made in header sync |  |
| **headerSyncStallBanTimeout** | **kotlin.collections.List&lt;kotlin.Int&gt;** | How much time to wait before banning a peer in header sync if sync is too slow |  |
| **logSummaryPeriod** | **kotlin.collections.List&lt;kotlin.Int&gt;** | Period between logging summary information. |  |
| **logSummaryStyle** | [**LogSummaryStyle**](LogSummaryStyle.md) | Enable coloring of the logs |  |
| **maxBlockProductionDelay** | **kotlin.collections.List&lt;kotlin.Int&gt;** | Maximum wait for approvals before producing block. |  |
| **maxBlockWaitDelay** | **kotlin.collections.List&lt;kotlin.Int&gt;** | Maximum duration before skipping given height. |  |
| **minBlockProductionDelay** | **kotlin.collections.List&lt;kotlin.Int&gt;** | Minimum duration before producing block. |  |
| **minNumPeers** | **kotlin.Int** | Minimum number of peers to start syncing. |  |
| **numBlockProducerSeats** | **kotlin.Int** | Number of block producer seats |  |
| **orphanStateWitnessMaxSize** | **kotlin.Int** | Maximum size of state witnesses in the OrphanStateWitnessPool.  We keep only orphan witnesses which are smaller than this size. This limits the maximum memory usage of OrphanStateWitnessPool. |  |
| **orphanStateWitnessPoolSize** | **kotlin.Int** | OrphanStateWitnessPool keeps instances of ChunkStateWitness which can&#39;t be processed because the previous block isn&#39;t available. The witnesses wait in the pool until the required block appears. This variable controls how many witnesses can be stored in the pool. |  |
| **produceChunkAddTransactionsTimeLimit** | **kotlin.String** | Limit the time of adding transactions to a chunk. A node produces a chunk by adding transactions from the transaction pool until some limit is reached. This time limit ensures that adding transactions won&#39;t take longer than the specified duration, which helps to produce the chunk quickly. |  |
| **produceEmptyBlocks** | **kotlin.Boolean** | Produce empty blocks, use &#x60;false&#x60; for testing. |  |
| **protocolVersionCheck** | [**ProtocolVersionCheckConfig**](ProtocolVersionCheckConfig.md) | Determines whether client should exit if the protocol version is not supported for the next or next next epoch. |  |
| **reshardingConfig** | **kotlin.String** |  |  |
| **saveInvalidWitnesses** | **kotlin.Boolean** | Save observed instances of invalid ChunkStateWitness to the database in DBCol::InvalidChunkStateWitnesses. Saving invalid witnesses is useful for analysis and debugging. This option can cause extra load on the database and is not recommended for production use. |  |
| **saveLatestWitnesses** | **kotlin.Boolean** | Save observed instances of ChunkStateWitness to the database in DBCol::LatestChunkStateWitnesses. Saving the latest witnesses is useful for analysis and debugging. This option can cause extra load on the database and is not recommended for production use. |  |
| **saveTrieChanges** | **kotlin.Boolean** | save_trie_changes should be set to true iff - archive if false - non-archival nodes need trie changes to perform garbage collection - archive is true, cold_store is configured and migration to split_storage is finished - node working in split storage mode needs trie changes in order to do garbage collection on hot. |  |
| **saveTxOutcomes** | **kotlin.Boolean** | Whether to persist transaction outcomes to disk or not. |  |
| **skipSyncWait** | **kotlin.Boolean** | Skip waiting for sync (for testing or single node testnet). |  |
| **stateRequestServerThreads** | **kotlin.Int** | Number of threads for StateRequestActor pool. |  |
| **stateRequestThrottlePeriod** | **kotlin.collections.List&lt;kotlin.Int&gt;** | Number of seconds between state requests for view client. Throttling window for state requests (headers and parts). |  |
| **stateRequestsPerThrottlePeriod** | **kotlin.Int** | Maximum number of state requests served per throttle period |  |
| **stateSync** | [**StateSyncConfig**](StateSyncConfig.md) | Options for syncing state. |  |
| **stateSyncEnabled** | **kotlin.Boolean** | Whether to use the State Sync mechanism. If disabled, the node will do Block Sync instead of State Sync. |  |
| **stateSyncExternalBackoff** | **kotlin.collections.List&lt;kotlin.Int&gt;** | Additional waiting period after a failed request to external storage |  |
| **stateSyncExternalTimeout** | **kotlin.collections.List&lt;kotlin.Int&gt;** | How long to wait for a response from centralized state sync |  |
| **stateSyncP2pTimeout** | **kotlin.collections.List&lt;kotlin.Int&gt;** | How long to wait for a response from p2p state sync |  |
| **stateSyncRetryBackoff** | **kotlin.collections.List&lt;kotlin.Int&gt;** | How long to wait after a failed state sync request |  |
| **syncCheckPeriod** | **kotlin.collections.List&lt;kotlin.Int&gt;** | How often to check that we are not out of sync. |  |
| **syncHeightThreshold** | **kotlin.Int** | Sync height threshold: below this difference in height don&#39;t start syncing. |  |
| **syncMaxBlockRequests** | **kotlin.Int** | Maximum number of block requests to send to peers to sync |  |
| **syncStepPeriod** | **kotlin.collections.List&lt;kotlin.Int&gt;** | While syncing, how long to check for each step. |  |
| **trackedShardsConfig** | [**TrackedShardsConfig**](TrackedShardsConfig.md) |  |  |
| **transactionRequestHandlerThreads** | **kotlin.Int** |  |  |
| **ttlAccountIdRouter** | **kotlin.collections.List&lt;kotlin.Int&gt;** | Time to persist Accounts Id in the router without removing them. |  |
| **txRoutingHeightHorizon** | **kotlin.Int** | If the node is not a chunk producer within that many blocks, then route to upcoming chunk producers. |  |
| **version** | [**Version**](Version.md) | Version of the binary. |  |
| **viewClientThreads** | **kotlin.Int** | Number of threads for ViewClientActor pool. |  |
| **chunkDistributionNetwork** | [**ChunkDistributionNetworkConfig**](ChunkDistributionNetworkConfig.md) |  |  [optional] |
| **cloudArchivalReader** | [**CloudArchivalReaderConfig**](CloudArchivalReaderConfig.md) |  |  [optional] |
| **cloudArchivalWriter** | [**CloudArchivalWriterConfig**](CloudArchivalWriterConfig.md) |  |  [optional] |
| **maxGasBurntView** | **kotlin.Int** |  |  [optional] |
| **rpcAddr** | **kotlin.String** | Listening rpc port for status. |  [optional] |
| **transactionPoolSizeLimit** | **kotlin.Int** | Limit of the size of per-shard transaction pool measured in bytes. If not set, the size will be unbounded. |  [optional] |
| **trieViewerStateSizeLimit** | **kotlin.Int** | Upper bound of the byte size of contract state that is still viewable. None is no limit |  [optional] |



