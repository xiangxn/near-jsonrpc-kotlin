
# RuntimeFeesConfigView

## Properties
| Name | Type | Description | Notes |
| ------------ | ------------- | ------------- | ------------- |
| **actionCreationConfig** | [**ActionCreationConfigView**](ActionCreationConfigView.md) | Describes the cost of creating a certain action, &#x60;Action&#x60;. Includes all variants. |  |
| **actionReceiptCreationConfig** | [**Fee**](Fee.md) | Describes the cost of creating an action receipt, &#x60;ActionReceipt&#x60;, excluding the actual cost of actions. - &#x60;send&#x60; cost is burned when a receipt is created using &#x60;promise_create&#x60; or     &#x60;promise_batch_create&#x60; - &#x60;exec&#x60; cost is burned when the receipt is being executed. |  |
| **burntGasReward** | **kotlin.collections.List&lt;kotlin.Int&gt;** | Fraction of the burnt gas to reward to the contract account for execution. |  |
| **dataReceiptCreationConfig** | [**DataReceiptCreationConfigView**](DataReceiptCreationConfigView.md) | Describes the cost of creating a data receipt, &#x60;DataReceipt&#x60;. |  |
| **pessimisticGasPriceInflationRatio** | **kotlin.collections.List&lt;kotlin.Int&gt;** | Pessimistic gas price inflation ratio. |  |
| **storageUsageConfig** | [**StorageUsageConfigView**](StorageUsageConfigView.md) | Describes fees for storage. |  |



