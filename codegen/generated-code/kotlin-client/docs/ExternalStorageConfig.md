
# ExternalStorageConfig

## Properties
| Name | Type | Description | Notes |
| ------------ | ------------- | ------------- | ------------- |
| **location** | [**ExternalStorageLocation**](ExternalStorageLocation.md) | Location of state parts. |  |
| **externalStorageFallbackThreshold** | **kotlin.Int** | The number of attempts the node will make to obtain a part from peers in the network before it fetches from external storage. |  [optional] |
| **numConcurrentRequests** | **kotlin.Int** | When fetching state parts from external storage, throttle fetch requests to this many concurrent requests. |  [optional] |
| **numConcurrentRequestsDuringCatchup** | **kotlin.Int** | During catchup, the node will use a different number of concurrent requests to reduce the performance impact of state sync. |  [optional] |



