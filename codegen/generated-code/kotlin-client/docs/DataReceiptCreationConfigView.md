
# DataReceiptCreationConfigView

## Properties
| Name | Type | Description | Notes |
| ------------ | ------------- | ------------- | ------------- |
| **baseCost** | [**Fee**](Fee.md) | Base cost of creating a data receipt. Both &#x60;send&#x60; and &#x60;exec&#x60; costs are burned when a new receipt has input dependencies. The gas is charged for each input dependency. The dependencies are specified when a receipt is created using &#x60;promise_then&#x60; and &#x60;promise_batch_then&#x60;. NOTE: Any receipt with output dependencies will produce data receipts. Even if it fails. Even if the last action is not a function call (in case of success it will return empty value). |  |
| **costPerByte** | [**Fee**](Fee.md) | Additional cost per byte sent. Both &#x60;send&#x60; and &#x60;exec&#x60; costs are burned when a function call finishes execution and returns &#x60;N&#x60; bytes of data to every output dependency. For each output dependency the cost is &#x60;(send(sir) + exec()) * N&#x60;. |  |



