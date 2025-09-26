
# CallFunctionBySyncCheckpoint

## Properties
| Name | Type | Description | Notes |
| ------------ | ------------- | ------------- | ------------- |
| **syncCheckpoint** | [**SyncCheckpoint**](SyncCheckpoint.md) |  |  |
| **accountId** | **kotlin.String** | NEAR Account Identifier.  This is a unique, syntactically valid, human-readable account identifier on the NEAR network.  [See the crate-level docs for information about validation.](index.html#account-id-rules)  Also see [Error kind precedence](AccountId#error-kind-precedence).  ## Examples  &#x60;&#x60;&#x60; use near_account_id::AccountId;  let alice: AccountId &#x3D; \&quot;alice.near\&quot;.parse().unwrap();  assert!(\&quot;ƒelicia.near\&quot;.parse::&lt;AccountId&gt;().is_err()); // (ƒ is not f) &#x60;&#x60;&#x60; |  |
| **argsBase64** | **kotlin.String** | This type is used to mark function arguments.  NOTE: The main reason for this to exist (except the type-safety) is that the value is transparently serialized and deserialized as a base64-encoded string when serde is used (serde_json). |  |
| **methodName** | **kotlin.String** |  |  |
| **requestType** | [**inline**](#RequestType) |  |  |


<a id="RequestType"></a>
## Enum: request_type
| Name | Value |
| ---- | ----- |
| requestType | call_function |



