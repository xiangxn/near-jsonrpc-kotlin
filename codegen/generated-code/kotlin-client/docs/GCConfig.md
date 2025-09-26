
# GCConfig

## Properties
| Name | Type | Description | Notes |
| ------------ | ------------- | ------------- | ------------- |
| **gcBlocksLimit** | **kotlin.Int** | Maximum number of blocks to garbage collect at every garbage collection call. |  [optional] |
| **gcForkCleanStep** | **kotlin.Int** | Maximum number of height to go through at each garbage collection step when cleaning forks during garbage collection. |  [optional] |
| **gcNumEpochsToKeep** | **kotlin.Int** | Number of epochs for which we keep store data. |  [optional] |
| **gcStepPeriod** | [**DurationAsStdSchemaProvider**](DurationAsStdSchemaProvider.md) | How often gc should be run |  [optional] |



