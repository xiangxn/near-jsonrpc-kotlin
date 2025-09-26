
# RpcLightClientNextBlockResponse

## Properties
| Name | Type | Description | Notes |
| ------------ | ------------- | ------------- | ------------- |
| **approvalsAfterNext** | [**kotlin.collections.List&lt;BlockHeaderViewApprovalsInner&gt;**](BlockHeaderViewApprovalsInner.md) |  |  [optional] |
| **innerLite** | [**BlockHeaderInnerLiteView**](BlockHeaderInnerLiteView.md) | Inner part of the block header that gets hashed, split into two parts, one that is sent    to light clients, and the rest |  [optional] |
| **innerRestHash** | **kotlin.String** |  |  [optional] |
| **nextBlockInnerHash** | **kotlin.String** |  |  [optional] |
| **nextBps** | [**kotlin.collections.List&lt;ValidatorStakeView&gt;**](ValidatorStakeView.md) |  |  [optional] |
| **prevBlockHash** | **kotlin.String** |  |  [optional] |



