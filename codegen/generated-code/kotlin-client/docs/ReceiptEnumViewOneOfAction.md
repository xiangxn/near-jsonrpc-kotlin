
# ReceiptEnumViewOneOfAction

## Properties
| Name | Type | Description | Notes |
| ------------ | ------------- | ------------- | ------------- |
| **actions** | [**kotlin.collections.List&lt;ActionView&gt;**](ActionView.md) |  |  |
| **gasPrice** | **kotlin.String** |  |  |
| **inputDataIds** | **kotlin.collections.List&lt;kotlin.String&gt;** |  |  |
| **outputDataReceivers** | [**kotlin.collections.List&lt;DataReceiverView&gt;**](DataReceiverView.md) |  |  |
| **signerId** | **kotlin.String** | NEAR Account Identifier.  This is a unique, syntactically valid, human-readable account identifier on the NEAR network.  [See the crate-level docs for information about validation.](index.html#account-id-rules)  Also see [Error kind precedence](AccountId#error-kind-precedence).  ## Examples  &#x60;&#x60;&#x60; use near_account_id::AccountId;  let alice: AccountId &#x3D; \&quot;alice.near\&quot;.parse().unwrap();  assert!(\&quot;ƒelicia.near\&quot;.parse::&lt;AccountId&gt;().is_err()); // (ƒ is not f) &#x60;&#x60;&#x60; |  |
| **signerPublicKey** | **kotlin.String** |  |  |
| **isPromiseYield** | **kotlin.Boolean** |  |  [optional] |



