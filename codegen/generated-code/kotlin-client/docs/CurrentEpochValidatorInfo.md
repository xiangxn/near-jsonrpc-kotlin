
# CurrentEpochValidatorInfo

## Properties
| Name | Type | Description | Notes |
| ------------ | ------------- | ------------- | ------------- |
| **accountId** | **kotlin.String** | NEAR Account Identifier.  This is a unique, syntactically valid, human-readable account identifier on the NEAR network.  [See the crate-level docs for information about validation.](index.html#account-id-rules)  Also see [Error kind precedence](AccountId#error-kind-precedence).  ## Examples  &#x60;&#x60;&#x60; use near_account_id::AccountId;  let alice: AccountId &#x3D; \&quot;alice.near\&quot;.parse().unwrap();  assert!(\&quot;ƒelicia.near\&quot;.parse::&lt;AccountId&gt;().is_err()); // (ƒ is not f) &#x60;&#x60;&#x60; |  |
| **isSlashed** | **kotlin.Boolean** |  |  |
| **numExpectedBlocks** | **kotlin.Int** |  |  |
| **numProducedBlocks** | **kotlin.Int** |  |  |
| **publicKey** | **kotlin.String** |  |  |
| **shards** | **kotlin.collections.List&lt;kotlin.Int&gt;** | Shards this validator is assigned to as chunk producer in the current epoch. |  |
| **stake** | **kotlin.String** |  |  |
| **numExpectedChunks** | **kotlin.Int** |  |  [optional] |
| **numExpectedChunksPerShard** | **kotlin.collections.List&lt;kotlin.Int&gt;** | Number of chunks this validator was expected to produce in each shard. Each entry in the array corresponds to the shard in the &#x60;shards_produced&#x60; array. |  [optional] |
| **numExpectedEndorsements** | **kotlin.Int** |  |  [optional] |
| **numExpectedEndorsementsPerShard** | **kotlin.collections.List&lt;kotlin.Int&gt;** | Number of chunks this validator was expected to validate and endorse in each shard. Each entry in the array corresponds to the shard in the &#x60;shards_endorsed&#x60; array. |  [optional] |
| **numProducedChunks** | **kotlin.Int** |  |  [optional] |
| **numProducedChunksPerShard** | **kotlin.collections.List&lt;kotlin.Int&gt;** |  |  [optional] |
| **numProducedEndorsements** | **kotlin.Int** |  |  [optional] |
| **numProducedEndorsementsPerShard** | **kotlin.collections.List&lt;kotlin.Int&gt;** |  |  [optional] |
| **shardsEndorsed** | **kotlin.collections.List&lt;kotlin.Int&gt;** | Shards this validator is assigned to as chunk validator in the current epoch. |  [optional] |



