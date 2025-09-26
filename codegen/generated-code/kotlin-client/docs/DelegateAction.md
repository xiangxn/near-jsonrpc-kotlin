
# DelegateAction

## Properties
| Name | Type | Description | Notes |
| ------------ | ------------- | ------------- | ------------- |
| **actions** | [**kotlin.collections.List&lt;NonDelegateAction&gt;**](NonDelegateAction.md) | List of actions to be executed.  With the meta transactions MVP defined in NEP-366, nested DelegateActions are not allowed. A separate type is used to enforce it. |  |
| **maxBlockHeight** | **kotlin.Int** | The maximal height of the block in the blockchain below which the given DelegateAction is valid. |  |
| **nonce** | **kotlin.Int** | Nonce to ensure that the same delegate action is not sent twice by a relayer and should match for given account&#39;s &#x60;public_key&#x60;. After this action is processed it will increment. |  |
| **publicKey** | **kotlin.String** | Public key used to sign this delegated action. |  |
| **receiverId** | **kotlin.String** | Receiver of the delegated actions. |  |
| **senderId** | **kotlin.String** | Signer of the delegated actions |  |



