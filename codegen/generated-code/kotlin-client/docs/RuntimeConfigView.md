
# RuntimeConfigView

## Properties
| Name | Type | Description | Notes |
| ------------ | ------------- | ------------- | ------------- |
| **accountCreationConfig** | [**AccountCreationConfigView**](AccountCreationConfigView.md) | Config that defines rules for account creation. |  |
| **congestionControlConfig** | [**CongestionControlConfigView**](CongestionControlConfigView.md) | The configuration for congestion control. |  |
| **storageAmountPerByte** | **kotlin.String** | Amount of yN per byte required to have on the account.  See &lt;https://nomicon.io/Economics/Economic#state-stake&gt; for details. |  |
| **transactionCosts** | [**RuntimeFeesConfigView**](RuntimeFeesConfigView.md) | Costs of different actions that need to be performed when sending and processing transaction and receipts. |  |
| **wasmConfig** | [**VMConfigView**](VMConfigView.md) | Config of wasm operations. |  |
| **witnessConfig** | [**WitnessConfigView**](WitnessConfigView.md) | Configuration specific to ChunkStateWitness. |  |



