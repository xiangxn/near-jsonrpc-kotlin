
# RpcProtocolConfigResponse

## Properties
| Name | Type | Description | Notes |
| ------------ | ------------- | ------------- | ------------- |
| **avgHiddenValidatorSeatsPerShard** | **kotlin.collections.List&lt;kotlin.Int&gt;** | Expected number of hidden validators per shard. |  |
| **blockProducerKickoutThreshold** | **kotlin.Int** | Threshold for kicking out block producers, between 0 and 100. |  |
| **chainId** | **kotlin.String** | ID of the blockchain. This must be unique for every blockchain. If your testnet blockchains do not have unique chain IDs, you will have a bad time. |  |
| **chunkProducerKickoutThreshold** | **kotlin.Int** | Threshold for kicking out chunk producers, between 0 and 100. |  |
| **chunkValidatorOnlyKickoutThreshold** | **kotlin.Int** | Threshold for kicking out nodes which are only chunk validators, between 0 and 100. |  |
| **dynamicResharding** | **kotlin.Boolean** | Enable dynamic re-sharding. |  |
| **epochLength** | **kotlin.Int** | Epoch length counted in block heights. |  |
| **fishermenThreshold** | **kotlin.String** | Fishermen stake threshold. |  |
| **gasLimit** | **kotlin.Int** | Initial gas limit. |  |
| **gasPriceAdjustmentRate** | **kotlin.collections.List&lt;kotlin.Int&gt;** | Gas price adjustment rate |  |
| **genesisHeight** | **kotlin.Int** | Height of genesis block. |  |
| **genesisTime** | [**java.time.OffsetDateTime**](java.time.OffsetDateTime.md) | Official time of blockchain start. |  |
| **maxGasPrice** | **kotlin.String** | Maximum gas price. |  |
| **maxInflationRate** | **kotlin.collections.List&lt;kotlin.Int&gt;** | Maximum inflation on the total supply every epoch. |  |
| **maxKickoutStakePerc** | **kotlin.Int** | Max stake percentage of the validators we will kick out. |  |
| **minGasPrice** | **kotlin.String** | Minimum gas price. It is also the initial gas price. |  |
| **minimumStakeDivisor** | **kotlin.Int** | The minimum stake required for staking is last seat price divided by this number. |  |
| **minimumStakeRatio** | **kotlin.collections.List&lt;kotlin.Int&gt;** | The lowest ratio s/s_total any block producer can have. See &lt;https://github.com/near/NEPs/pull/167&gt; for details |  |
| **minimumValidatorsPerShard** | **kotlin.Int** | The minimum number of validators each shard must have |  |
| **numBlockProducerSeats** | **kotlin.Int** | Number of block producer seats at genesis. |  |
| **numBlockProducerSeatsPerShard** | **kotlin.collections.List&lt;kotlin.Int&gt;** | Defines number of shards and number of block producer seats per each shard at genesis. |  |
| **numBlocksPerYear** | **kotlin.Int** | Expected number of blocks per year |  |
| **onlineMaxThreshold** | **kotlin.collections.List&lt;kotlin.Int&gt;** | Online maximum threshold above which validator gets full reward. |  |
| **onlineMinThreshold** | **kotlin.collections.List&lt;kotlin.Int&gt;** | Online minimum threshold below which validator doesn&#39;t receive reward. |  |
| **protocolRewardRate** | **kotlin.collections.List&lt;kotlin.Int&gt;** | Protocol treasury rate |  |
| **protocolTreasuryAccount** | **kotlin.String** | Protocol treasury account |  |
| **protocolUpgradeStakeThreshold** | **kotlin.collections.List&lt;kotlin.Int&gt;** | Threshold of stake that needs to indicate that they ready for upgrade. |  |
| **protocolVersion** | **kotlin.Int** | Current Protocol Version |  |
| **runtimeConfig** | [**RuntimeConfigView**](RuntimeConfigView.md) | Runtime configuration (mostly economics constants). |  |
| **shardLayout** | [**ShardLayout**](ShardLayout.md) | Layout information regarding how to split accounts to shards |  |
| **shuffleShardAssignmentForChunkProducers** | **kotlin.Boolean** | If true, shuffle the chunk producers across shards. In other words, if the shard assignments were &#x60;[S_0, S_1, S_2, S_3]&#x60; where &#x60;S_i&#x60; represents the set of chunk producers for shard &#x60;i&#x60;, if this flag were true, the shard assignments might become, for example, &#x60;[S_2, S_0, S_3, S_1]&#x60;. |  |
| **targetValidatorMandatesPerShard** | **kotlin.Int** | Number of target chunk validator mandates for each shard. |  |
| **transactionValidityPeriod** | **kotlin.Int** | Number of blocks for which a given transaction is valid |  |



