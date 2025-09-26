
# DataChangesBySyncCheckpoint

## Properties
| Name | Type | Description | Notes |
| ------------ | ------------- | ------------- | ------------- |
| **syncCheckpoint** | [**SyncCheckpoint**](SyncCheckpoint.md) |  |  |
| **accountIds** | **kotlin.collections.List&lt;kotlin.String&gt;** |  |  |
| **changesType** | [**inline**](#ChangesType) |  |  |
| **keyPrefixBase64** | **kotlin.String** | This type is used to mark keys (arrays of bytes) that are queried from store.  NOTE: Currently, this type is only used in the view_client and RPC to be able to transparently pretty-serialize the bytes arrays as base64-encoded strings (see &#x60;serialize.rs&#x60;). |  |


<a id="ChangesType"></a>
## Enum: changes_type
| Name | Value |
| ---- | ----- |
| changesType | data_changes |



