
# AccessKey

## Properties
| Name | Type | Description | Notes |
| ------------ | ------------- | ------------- | ------------- |
| **nonce** | **kotlin.Int** | Nonce for this access key, used for tx nonce generation. When access key is created, nonce is set to &#x60;(block_height - 1) * 1e6&#x60; to avoid tx hash collision on access key re-creation. See &lt;https://github.com/near/nearcore/issues/3779&gt; for more details. |  |
| **permission** | [**AccessKeyPermission**](AccessKeyPermission.md) | Defines permissions for this access key. |  |



