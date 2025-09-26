
# RpcQueryRequest

## Properties
| Name | Type | Description | Notes |
| ------------ | ------------- | ------------- | ------------- |
| **blockId** | [**BlockId**](BlockId.md) |  |  |
| **accountId** | **kotlin.String** | NEAR Account Identifier.  This is a unique, syntactically valid, human-readable account identifier on the NEAR network.  [See the crate-level docs for information about validation.](index.html#account-id-rules)  Also see [Error kind precedence](AccountId#error-kind-precedence).  ## Examples  &#x60;&#x60;&#x60; use near_account_id::AccountId;  let alice: AccountId &#x3D; \&quot;alice.near\&quot;.parse().unwrap();  assert!(\&quot;ƒelicia.near\&quot;.parse::&lt;AccountId&gt;().is_err()); // (ƒ is not f) &#x60;&#x60;&#x60; |  |
| **requestType** | [**inline**](#RequestType) |  |  |
| **prefixBase64** | **kotlin.String** | This type is used to mark keys (arrays of bytes) that are queried from store.  NOTE: Currently, this type is only used in the view_client and RPC to be able to transparently pretty-serialize the bytes arrays as base64-encoded strings (see &#x60;serialize.rs&#x60;). |  |
| **publicKey** | **kotlin.String** |  |  |
| **argsBase64** | **kotlin.String** | This type is used to mark function arguments.  NOTE: The main reason for this to exist (except the type-safety) is that the value is transparently serialized and deserialized as a base64-encoded string when serde is used (serde_json). |  |
| **methodName** | **kotlin.String** |  |  |
| **codeHash** | **kotlin.String** |  |  |
| **finality** | [**Finality**](Finality.md) |  |  |
| **syncCheckpoint** | [**SyncCheckpoint**](SyncCheckpoint.md) |  |  |
| **includeProof** | **kotlin.Boolean** |  |  [optional] |


<a id="RequestType"></a>
## Enum: request_type
| Name | Value |
| ---- | ----- |
| requestType | view_global_contract_code_by_account_id |



