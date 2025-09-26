
# StateItem

## Properties
| Name | Type | Description | Notes |
| ------------ | ------------- | ------------- | ------------- |
| **key** | **kotlin.String** | This type is used to mark keys (arrays of bytes) that are queried from store.  NOTE: Currently, this type is only used in the view_client and RPC to be able to transparently pretty-serialize the bytes arrays as base64-encoded strings (see &#x60;serialize.rs&#x60;). |  |
| **&#x60;value&#x60;** | **kotlin.String** | This type is used to mark values returned from store (arrays of bytes).  NOTE: Currently, this type is only used in the view_client and RPC to be able to transparently pretty-serialize the bytes arrays as base64-encoded strings (see &#x60;serialize.rs&#x60;). |  |



