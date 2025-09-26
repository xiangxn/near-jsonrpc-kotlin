
# WitnessConfigView

## Properties
| Name | Type | Description | Notes |
| ------------ | ------------- | ------------- | ------------- |
| **combinedTransactionsSizeLimit** | **kotlin.Int** | Maximum size of transactions contained inside ChunkStateWitness.  A witness contains transactions from both the previous chunk and the current one. This parameter limits the sum of sizes of transactions from both of those chunks. |  |
| **mainStorageProofSizeSoftLimit** | **kotlin.Int** | Size limit for storage proof generated while executing receipts in a chunk. After this limit is reached we defer execution of any new receipts. |  |
| **newTransactionsValidationStateSizeSoftLimit** | **kotlin.Int** | Soft size limit of storage proof used to validate new transactions in ChunkStateWitness. |  |



