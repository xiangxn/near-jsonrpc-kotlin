
# CloudArchivalWriterConfig

## Properties
| Name | Type | Description | Notes |
| ------------ | ------------- | ------------- | ------------- |
| **cloudStorage** | [**CloudStorageConfig**](CloudStorageConfig.md) | Configures the external storage used by the archival node. |  |
| **archiveBlockData** | **kotlin.Boolean** | Determines whether block-related data should be written to cloud storage. |  [optional] |
| **pollingInterval** | [**DurationAsStdSchemaProvider**](DurationAsStdSchemaProvider.md) | Interval at which the system checks for new blocks or chunks to archive. |  [optional] |



