
# FunctionCallPermission

## Properties
| Name | Type | Description | Notes |
| ------------ | ------------- | ------------- | ------------- |
| **methodNames** | **kotlin.collections.List&lt;kotlin.String&gt;** | A list of method names that can be used. The access key only allows transactions with the function call of one of the given method names. Empty list means any method name can be used. |  |
| **receiverId** | **kotlin.String** | The access key only allows transactions with the given receiver&#39;s account id. |  |
| **allowance** | **kotlin.String** | Allowance is a balance limit to use by this access key to pay for function call gas and transaction fees. When this access key is used, both account balance and the allowance is decreased by the same value. &#x60;None&#x60; means unlimited allowance. NOTE: To change or increase the allowance, the old access key needs to be deleted and a new access key should be created. |  [optional] |



