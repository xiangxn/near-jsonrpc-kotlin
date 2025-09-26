
# RpcTransactionResponse

## Properties
| Name | Type | Description | Notes |
| ------------ | ------------- | ------------- | ------------- |
| **finalExecutionStatus** | [**TxExecutionStatus**](TxExecutionStatus.md) |  |  |
| **receipts** | [**kotlin.collections.List&lt;ReceiptView&gt;**](ReceiptView.md) | Receipts generated from the transaction |  |
| **receiptsOutcome** | [**kotlin.collections.List&lt;ExecutionOutcomeWithIdView&gt;**](ExecutionOutcomeWithIdView.md) | The execution outcome of receipts. |  |
| **status** | [**FinalExecutionStatus**](FinalExecutionStatus.md) | Execution status defined by chain.rs:get_final_transaction_result FinalExecutionStatus::NotStarted - the tx is not converted to the receipt yet FinalExecutionStatus::Started - we have at least 1 receipt, but the first leaf receipt_id (using dfs) hasn&#39;t finished the execution FinalExecutionStatus::Failure - the result of the first leaf receipt_id FinalExecutionStatus::SuccessValue - the result of the first leaf receipt_id |  |
| **transaction** | [**SignedTransactionView**](SignedTransactionView.md) | Signed Transaction |  |
| **transactionOutcome** | [**ExecutionOutcomeWithIdView**](ExecutionOutcomeWithIdView.md) | The execution outcome of the signed transaction. |  |



