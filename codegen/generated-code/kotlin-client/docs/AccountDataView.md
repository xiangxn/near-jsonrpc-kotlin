
# AccountDataView

## Properties
| Name | Type | Description | Notes |
| ------------ | ------------- | ------------- | ------------- |
| **accountKey** | **kotlin.String** | Account key of the validator signing this AccountData. |  |
| **peerId** | **kotlin.String** | ID of the node that handles the account key (aka validator key). |  |
| **proxies** | [**kotlin.collections.List&lt;Tier1ProxyView&gt;**](Tier1ProxyView.md) | Proxy nodes that are directly connected to the validator node (this list may include the validator node itself). TIER1 nodes should connect to one of the proxies to sent TIER1 messages to the validator. |  |
| **timestamp** | **kotlin.String** | UTC timestamp of when the AccountData has been signed. |  |



