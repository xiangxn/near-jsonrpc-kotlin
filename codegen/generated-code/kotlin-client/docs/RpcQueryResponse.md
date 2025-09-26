
# RpcQueryResponse

## Properties
| Name | Type | Description | Notes |
| ------------ | ------------- | ------------- | ------------- |
| **blockHash** | **kotlin.String** |  |  |
| **blockHeight** | **kotlin.Int** |  |  |
| **amount** | **kotlin.String** |  |  |
| **codeHash** | **kotlin.String** |  |  |
| **locked** | **kotlin.String** |  |  |
| **storageUsage** | **kotlin.Int** |  |  |
| **codeBase64** | **kotlin.String** |  |  |
| **hash** | **kotlin.String** |  |  |
| **propertyValues** | [**kotlin.collections.List&lt;StateItem&gt;**](StateItem.md) |  |  |
| **logs** | **kotlin.collections.List&lt;kotlin.String&gt;** |  |  |
| **result** | **kotlin.collections.List&lt;kotlin.Int&gt;** |  |  |
| **nonce** | **kotlin.Int** |  |  |
| **permission** | [**AccessKeyPermissionView**](AccessKeyPermissionView.md) |  |  |
| **propertyKeys** | [**kotlin.collections.List&lt;AccessKeyInfoView&gt;**](AccessKeyInfoView.md) |  |  |
| **globalContractAccountId** | **kotlin.String** | NEAR Account Identifier.  This is a unique, syntactically valid, human-readable account identifier on the NEAR network.  [See the crate-level docs for information about validation.](index.html#account-id-rules)  Also see [Error kind precedence](AccountId#error-kind-precedence).  ## Examples  &#x60;&#x60;&#x60; use near_account_id::AccountId;  let alice: AccountId &#x3D; \&quot;alice.near\&quot;.parse().unwrap();  assert!(\&quot;ƒelicia.near\&quot;.parse::&lt;AccountId&gt;().is_err()); // (ƒ is not f) &#x60;&#x60;&#x60; |  [optional] |
| **globalContractHash** | **kotlin.String** |  |  [optional] |
| **storagePaidAt** | **kotlin.Int** | TODO(2271): deprecated. |  [optional] |
| **proof** | **kotlin.collections.List&lt;kotlin.String&gt;** |  |  [optional] |



