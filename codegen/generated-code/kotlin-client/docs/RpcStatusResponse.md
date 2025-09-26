
# RpcStatusResponse

## Properties
| Name | Type | Description | Notes |
| ------------ | ------------- | ------------- | ------------- |
| **chainId** | **kotlin.String** | Unique chain id. |  |
| **genesisHash** | **kotlin.String** | Genesis hash of the chain. |  |
| **latestProtocolVersion** | **kotlin.Int** | Latest protocol version that this client supports. |  |
| **nodePublicKey** | **kotlin.String** | Public key of the node. |  |
| **protocolVersion** | **kotlin.Int** | Currently active protocol version. |  |
| **syncInfo** | [**StatusSyncInfo**](StatusSyncInfo.md) | Sync status of the node. |  |
| **uptimeSec** | **kotlin.Long** | Uptime of the node. |  |
| **validators** | [**kotlin.collections.List&lt;ValidatorInfo&gt;**](ValidatorInfo.md) | Current epoch validators. |  |
| **version** | [**Version**](Version.md) | Binary version. |  |
| **detailedDebugStatus** | [**DetailedDebugStatus**](DetailedDebugStatus.md) |  |  [optional] |
| **nodeKey** | **kotlin.String** |  |  [optional] |
| **rpcAddr** | **kotlin.String** | Address for RPC server.  None if node doesn&#39;t have RPC endpoint enabled. |  [optional] |
| **validatorAccountId** | **kotlin.String** | NEAR Account Identifier.  This is a unique, syntactically valid, human-readable account identifier on the NEAR network.  [See the crate-level docs for information about validation.](index.html#account-id-rules)  Also see [Error kind precedence](AccountId#error-kind-precedence).  ## Examples  &#x60;&#x60;&#x60; use near_account_id::AccountId;  let alice: AccountId &#x3D; \&quot;alice.near\&quot;.parse().unwrap();  assert!(\&quot;ƒelicia.near\&quot;.parse::&lt;AccountId&gt;().is_err()); // (ƒ is not f) &#x60;&#x60;&#x60; |  [optional] |
| **validatorPublicKey** | **kotlin.String** |  |  [optional] |



