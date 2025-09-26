
# DumpConfig

## Properties
| Name | Type | Description | Notes |
| ------------ | ------------- | ------------- | ------------- |
| **location** | [**ExternalStorageLocation**](ExternalStorageLocation.md) | Specifies where to write the obtained state parts. |  |
| **credentialsFile** | **kotlin.String** | Location of a json file with credentials allowing access to the bucket. |  [optional] |
| **iterationDelay** | [**DurationAsStdSchemaProvider**](DurationAsStdSchemaProvider.md) |  |  [optional] |
| **restartDumpForShards** | **kotlin.collections.List&lt;kotlin.Int&gt;** | Use in case a node that dumps state to the external storage gets in trouble. |  [optional] |



