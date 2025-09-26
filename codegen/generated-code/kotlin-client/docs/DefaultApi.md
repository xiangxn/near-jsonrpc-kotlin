# DefaultApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**block**](DefaultApi.md#block) | **POST** /block |  |
| [**blockEffects**](DefaultApi.md#blockEffects) | **POST** /block_effects |  |
| [**broadcastTxAsync**](DefaultApi.md#broadcastTxAsync) | **POST** /broadcast_tx_async |  |
| [**broadcastTxCommit**](DefaultApi.md#broadcastTxCommit) | **POST** /broadcast_tx_commit |  |
| [**changes**](DefaultApi.md#changes) | **POST** /changes |  |
| [**chunk**](DefaultApi.md#chunk) | **POST** /chunk |  |
| [**clientConfig**](DefaultApi.md#clientConfig) | **POST** /client_config |  |
| [**eXPERIMENTALChanges**](DefaultApi.md#eXPERIMENTALChanges) | **POST** /EXPERIMENTAL_changes |  |
| [**eXPERIMENTALChangesInBlock**](DefaultApi.md#eXPERIMENTALChangesInBlock) | **POST** /EXPERIMENTAL_changes_in_block |  |
| [**eXPERIMENTALCongestionLevel**](DefaultApi.md#eXPERIMENTALCongestionLevel) | **POST** /EXPERIMENTAL_congestion_level |  |
| [**eXPERIMENTALGenesisConfig**](DefaultApi.md#eXPERIMENTALGenesisConfig) | **POST** /EXPERIMENTAL_genesis_config |  |
| [**eXPERIMENTALLightClientBlockProof**](DefaultApi.md#eXPERIMENTALLightClientBlockProof) | **POST** /EXPERIMENTAL_light_client_block_proof |  |
| [**eXPERIMENTALLightClientProof**](DefaultApi.md#eXPERIMENTALLightClientProof) | **POST** /EXPERIMENTAL_light_client_proof |  |
| [**eXPERIMENTALMaintenanceWindows**](DefaultApi.md#eXPERIMENTALMaintenanceWindows) | **POST** /EXPERIMENTAL_maintenance_windows |  |
| [**eXPERIMENTALProtocolConfig**](DefaultApi.md#eXPERIMENTALProtocolConfig) | **POST** /EXPERIMENTAL_protocol_config |  |
| [**eXPERIMENTALReceipt**](DefaultApi.md#eXPERIMENTALReceipt) | **POST** /EXPERIMENTAL_receipt |  |
| [**eXPERIMENTALSplitStorageInfo**](DefaultApi.md#eXPERIMENTALSplitStorageInfo) | **POST** /EXPERIMENTAL_split_storage_info |  |
| [**eXPERIMENTALTxStatus**](DefaultApi.md#eXPERIMENTALTxStatus) | **POST** /EXPERIMENTAL_tx_status |  |
| [**eXPERIMENTALValidatorsOrdered**](DefaultApi.md#eXPERIMENTALValidatorsOrdered) | **POST** /EXPERIMENTAL_validators_ordered |  |
| [**gasPrice**](DefaultApi.md#gasPrice) | **POST** /gas_price |  |
| [**genesisConfig**](DefaultApi.md#genesisConfig) | **POST** /genesis_config |  |
| [**health**](DefaultApi.md#health) | **POST** /health |  |
| [**lightClientProof**](DefaultApi.md#lightClientProof) | **POST** /light_client_proof |  |
| [**maintenanceWindows**](DefaultApi.md#maintenanceWindows) | **POST** /maintenance_windows |  |
| [**networkInfo**](DefaultApi.md#networkInfo) | **POST** /network_info |  |
| [**nextLightClientBlock**](DefaultApi.md#nextLightClientBlock) | **POST** /next_light_client_block |  |
| [**query**](DefaultApi.md#query) | **POST** /query |  |
| [**sendTx**](DefaultApi.md#sendTx) | **POST** /send_tx |  |
| [**status**](DefaultApi.md#status) | **POST** /status |  |
| [**tx**](DefaultApi.md#tx) | **POST** /tx |  |
| [**validators**](DefaultApi.md#validators) | **POST** /validators |  |


<a id="block"></a>
# **block**
> JsonRpcResponseForRpcBlockResponseAndRpcError block(jsonRpcRequestForBlock)



Returns block details for given height or hash

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = DefaultApi()
val jsonRpcRequestForBlock : JsonRpcRequestForBlock =  // JsonRpcRequestForBlock | 
try {
    val result : JsonRpcResponseForRpcBlockResponseAndRpcError = apiInstance.block(jsonRpcRequestForBlock)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#block")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#block")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **jsonRpcRequestForBlock** | [**JsonRpcRequestForBlock**](JsonRpcRequestForBlock.md)|  | |

### Return type

[**JsonRpcResponseForRpcBlockResponseAndRpcError**](JsonRpcResponseForRpcBlockResponseAndRpcError.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a id="blockEffects"></a>
# **blockEffects**
> JsonRpcResponseForRpcStateChangesInBlockByTypeResponseAndRpcError blockEffects(jsonRpcRequestForBlockEffects)



Returns changes in block for given block height or hash over all transactions for all the types. Includes changes like account_touched, access_key_touched, data_touched, contract_code_touched.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = DefaultApi()
val jsonRpcRequestForBlockEffects : JsonRpcRequestForBlockEffects =  // JsonRpcRequestForBlockEffects | 
try {
    val result : JsonRpcResponseForRpcStateChangesInBlockByTypeResponseAndRpcError = apiInstance.blockEffects(jsonRpcRequestForBlockEffects)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#blockEffects")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#blockEffects")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **jsonRpcRequestForBlockEffects** | [**JsonRpcRequestForBlockEffects**](JsonRpcRequestForBlockEffects.md)|  | |

### Return type

[**JsonRpcResponseForRpcStateChangesInBlockByTypeResponseAndRpcError**](JsonRpcResponseForRpcStateChangesInBlockByTypeResponseAndRpcError.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a id="broadcastTxAsync"></a>
# **broadcastTxAsync**
> JsonRpcResponseForCryptoHashAndRpcError broadcastTxAsync(jsonRpcRequestForBroadcastTxAsync)



[Deprecated] Sends a transaction and immediately returns transaction hash. Consider using send_tx instead.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = DefaultApi()
val jsonRpcRequestForBroadcastTxAsync : JsonRpcRequestForBroadcastTxAsync =  // JsonRpcRequestForBroadcastTxAsync | 
try {
    val result : JsonRpcResponseForCryptoHashAndRpcError = apiInstance.broadcastTxAsync(jsonRpcRequestForBroadcastTxAsync)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#broadcastTxAsync")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#broadcastTxAsync")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **jsonRpcRequestForBroadcastTxAsync** | [**JsonRpcRequestForBroadcastTxAsync**](JsonRpcRequestForBroadcastTxAsync.md)|  | |

### Return type

[**JsonRpcResponseForCryptoHashAndRpcError**](JsonRpcResponseForCryptoHashAndRpcError.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a id="broadcastTxCommit"></a>
# **broadcastTxCommit**
> JsonRpcResponseForRpcTransactionResponseAndRpcError broadcastTxCommit(jsonRpcRequestForBroadcastTxCommit)



[Deprecated] Sends a transaction and waits until transaction is fully complete. (Has a 10 second timeout). Consider using send_tx instead.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = DefaultApi()
val jsonRpcRequestForBroadcastTxCommit : JsonRpcRequestForBroadcastTxCommit =  // JsonRpcRequestForBroadcastTxCommit | 
try {
    val result : JsonRpcResponseForRpcTransactionResponseAndRpcError = apiInstance.broadcastTxCommit(jsonRpcRequestForBroadcastTxCommit)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#broadcastTxCommit")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#broadcastTxCommit")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **jsonRpcRequestForBroadcastTxCommit** | [**JsonRpcRequestForBroadcastTxCommit**](JsonRpcRequestForBroadcastTxCommit.md)|  | |

### Return type

[**JsonRpcResponseForRpcTransactionResponseAndRpcError**](JsonRpcResponseForRpcTransactionResponseAndRpcError.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a id="changes"></a>
# **changes**
> JsonRpcResponseForRpcStateChangesInBlockResponseAndRpcError changes(jsonRpcRequestForChanges)



Returns changes for a given account, contract or contract code for given block height or hash.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = DefaultApi()
val jsonRpcRequestForChanges : JsonRpcRequestForChanges =  // JsonRpcRequestForChanges | 
try {
    val result : JsonRpcResponseForRpcStateChangesInBlockResponseAndRpcError = apiInstance.changes(jsonRpcRequestForChanges)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#changes")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#changes")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **jsonRpcRequestForChanges** | [**JsonRpcRequestForChanges**](JsonRpcRequestForChanges.md)|  | |

### Return type

[**JsonRpcResponseForRpcStateChangesInBlockResponseAndRpcError**](JsonRpcResponseForRpcStateChangesInBlockResponseAndRpcError.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a id="chunk"></a>
# **chunk**
> JsonRpcResponseForRpcChunkResponseAndRpcError chunk(jsonRpcRequestForChunk)



Returns details of a specific chunk. You can run a block details query to get a valid chunk hash.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = DefaultApi()
val jsonRpcRequestForChunk : JsonRpcRequestForChunk =  // JsonRpcRequestForChunk | 
try {
    val result : JsonRpcResponseForRpcChunkResponseAndRpcError = apiInstance.chunk(jsonRpcRequestForChunk)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#chunk")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#chunk")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **jsonRpcRequestForChunk** | [**JsonRpcRequestForChunk**](JsonRpcRequestForChunk.md)|  | |

### Return type

[**JsonRpcResponseForRpcChunkResponseAndRpcError**](JsonRpcResponseForRpcChunkResponseAndRpcError.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a id="clientConfig"></a>
# **clientConfig**
> JsonRpcResponseForRpcClientConfigResponseAndRpcError clientConfig(jsonRpcRequestForClientConfig)



Queries client node configuration

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = DefaultApi()
val jsonRpcRequestForClientConfig : JsonRpcRequestForClientConfig =  // JsonRpcRequestForClientConfig | 
try {
    val result : JsonRpcResponseForRpcClientConfigResponseAndRpcError = apiInstance.clientConfig(jsonRpcRequestForClientConfig)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#clientConfig")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#clientConfig")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **jsonRpcRequestForClientConfig** | [**JsonRpcRequestForClientConfig**](JsonRpcRequestForClientConfig.md)|  | |

### Return type

[**JsonRpcResponseForRpcClientConfigResponseAndRpcError**](JsonRpcResponseForRpcClientConfigResponseAndRpcError.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a id="eXPERIMENTALChanges"></a>
# **eXPERIMENTALChanges**
> JsonRpcResponseForRpcStateChangesInBlockResponseAndRpcError eXPERIMENTALChanges(jsonRpcRequestForEXPERIMENTALChanges)



[Deprecated] Returns changes for a given account, contract or contract code for given block height or hash. Consider using changes instead.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = DefaultApi()
val jsonRpcRequestForEXPERIMENTALChanges : JsonRpcRequestForEXPERIMENTALChanges =  // JsonRpcRequestForEXPERIMENTALChanges | 
try {
    val result : JsonRpcResponseForRpcStateChangesInBlockResponseAndRpcError = apiInstance.eXPERIMENTALChanges(jsonRpcRequestForEXPERIMENTALChanges)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#eXPERIMENTALChanges")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#eXPERIMENTALChanges")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **jsonRpcRequestForEXPERIMENTALChanges** | [**JsonRpcRequestForEXPERIMENTALChanges**](JsonRpcRequestForEXPERIMENTALChanges.md)|  | |

### Return type

[**JsonRpcResponseForRpcStateChangesInBlockResponseAndRpcError**](JsonRpcResponseForRpcStateChangesInBlockResponseAndRpcError.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a id="eXPERIMENTALChangesInBlock"></a>
# **eXPERIMENTALChangesInBlock**
> JsonRpcResponseForRpcStateChangesInBlockByTypeResponseAndRpcError eXPERIMENTALChangesInBlock(jsonRpcRequestForEXPERIMENTALChangesInBlock)



[Deprecated] Returns changes in block for given block height or hash over all transactions for all the types. Includes changes like account_touched, access_key_touched, data_touched, contract_code_touched. Consider using block_effects instead

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = DefaultApi()
val jsonRpcRequestForEXPERIMENTALChangesInBlock : JsonRpcRequestForEXPERIMENTALChangesInBlock =  // JsonRpcRequestForEXPERIMENTALChangesInBlock | 
try {
    val result : JsonRpcResponseForRpcStateChangesInBlockByTypeResponseAndRpcError = apiInstance.eXPERIMENTALChangesInBlock(jsonRpcRequestForEXPERIMENTALChangesInBlock)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#eXPERIMENTALChangesInBlock")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#eXPERIMENTALChangesInBlock")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **jsonRpcRequestForEXPERIMENTALChangesInBlock** | [**JsonRpcRequestForEXPERIMENTALChangesInBlock**](JsonRpcRequestForEXPERIMENTALChangesInBlock.md)|  | |

### Return type

[**JsonRpcResponseForRpcStateChangesInBlockByTypeResponseAndRpcError**](JsonRpcResponseForRpcStateChangesInBlockByTypeResponseAndRpcError.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a id="eXPERIMENTALCongestionLevel"></a>
# **eXPERIMENTALCongestionLevel**
> JsonRpcResponseForRpcCongestionLevelResponseAndRpcError eXPERIMENTALCongestionLevel(jsonRpcRequestForEXPERIMENTALCongestionLevel)



Queries the congestion level of a shard. More info about congestion [here](https://near.github.io/nearcore/architecture/how/receipt-congestion.html?highlight&#x3D;congestion#receipt-congestion)

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = DefaultApi()
val jsonRpcRequestForEXPERIMENTALCongestionLevel : JsonRpcRequestForEXPERIMENTALCongestionLevel =  // JsonRpcRequestForEXPERIMENTALCongestionLevel | 
try {
    val result : JsonRpcResponseForRpcCongestionLevelResponseAndRpcError = apiInstance.eXPERIMENTALCongestionLevel(jsonRpcRequestForEXPERIMENTALCongestionLevel)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#eXPERIMENTALCongestionLevel")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#eXPERIMENTALCongestionLevel")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **jsonRpcRequestForEXPERIMENTALCongestionLevel** | [**JsonRpcRequestForEXPERIMENTALCongestionLevel**](JsonRpcRequestForEXPERIMENTALCongestionLevel.md)|  | |

### Return type

[**JsonRpcResponseForRpcCongestionLevelResponseAndRpcError**](JsonRpcResponseForRpcCongestionLevelResponseAndRpcError.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a id="eXPERIMENTALGenesisConfig"></a>
# **eXPERIMENTALGenesisConfig**
> JsonRpcResponseForGenesisConfigAndRpcError eXPERIMENTALGenesisConfig(jsonRpcRequestForEXPERIMENTALGenesisConfig)



[Deprecated] Get initial state and parameters for the genesis block. Consider genesis_config instead.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = DefaultApi()
val jsonRpcRequestForEXPERIMENTALGenesisConfig : JsonRpcRequestForEXPERIMENTALGenesisConfig =  // JsonRpcRequestForEXPERIMENTALGenesisConfig | 
try {
    val result : JsonRpcResponseForGenesisConfigAndRpcError = apiInstance.eXPERIMENTALGenesisConfig(jsonRpcRequestForEXPERIMENTALGenesisConfig)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#eXPERIMENTALGenesisConfig")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#eXPERIMENTALGenesisConfig")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **jsonRpcRequestForEXPERIMENTALGenesisConfig** | [**JsonRpcRequestForEXPERIMENTALGenesisConfig**](JsonRpcRequestForEXPERIMENTALGenesisConfig.md)|  | |

### Return type

[**JsonRpcResponseForGenesisConfigAndRpcError**](JsonRpcResponseForGenesisConfigAndRpcError.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a id="eXPERIMENTALLightClientBlockProof"></a>
# **eXPERIMENTALLightClientBlockProof**
> JsonRpcResponseForRpcLightClientBlockProofResponseAndRpcError eXPERIMENTALLightClientBlockProof(jsonRpcRequestForEXPERIMENTALLightClientBlockProof)



Returns the proofs for a transaction execution.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = DefaultApi()
val jsonRpcRequestForEXPERIMENTALLightClientBlockProof : JsonRpcRequestForEXPERIMENTALLightClientBlockProof =  // JsonRpcRequestForEXPERIMENTALLightClientBlockProof | 
try {
    val result : JsonRpcResponseForRpcLightClientBlockProofResponseAndRpcError = apiInstance.eXPERIMENTALLightClientBlockProof(jsonRpcRequestForEXPERIMENTALLightClientBlockProof)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#eXPERIMENTALLightClientBlockProof")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#eXPERIMENTALLightClientBlockProof")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **jsonRpcRequestForEXPERIMENTALLightClientBlockProof** | [**JsonRpcRequestForEXPERIMENTALLightClientBlockProof**](JsonRpcRequestForEXPERIMENTALLightClientBlockProof.md)|  | |

### Return type

[**JsonRpcResponseForRpcLightClientBlockProofResponseAndRpcError**](JsonRpcResponseForRpcLightClientBlockProofResponseAndRpcError.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a id="eXPERIMENTALLightClientProof"></a>
# **eXPERIMENTALLightClientProof**
> JsonRpcResponseForRpcLightClientExecutionProofResponseAndRpcError eXPERIMENTALLightClientProof(jsonRpcRequestForEXPERIMENTALLightClientProof)



Returns the proofs for a transaction execution.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = DefaultApi()
val jsonRpcRequestForEXPERIMENTALLightClientProof : JsonRpcRequestForEXPERIMENTALLightClientProof =  // JsonRpcRequestForEXPERIMENTALLightClientProof | 
try {
    val result : JsonRpcResponseForRpcLightClientExecutionProofResponseAndRpcError = apiInstance.eXPERIMENTALLightClientProof(jsonRpcRequestForEXPERIMENTALLightClientProof)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#eXPERIMENTALLightClientProof")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#eXPERIMENTALLightClientProof")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **jsonRpcRequestForEXPERIMENTALLightClientProof** | [**JsonRpcRequestForEXPERIMENTALLightClientProof**](JsonRpcRequestForEXPERIMENTALLightClientProof.md)|  | |

### Return type

[**JsonRpcResponseForRpcLightClientExecutionProofResponseAndRpcError**](JsonRpcResponseForRpcLightClientExecutionProofResponseAndRpcError.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a id="eXPERIMENTALMaintenanceWindows"></a>
# **eXPERIMENTALMaintenanceWindows**
> JsonRpcResponseForArrayOfRangeOfUint64AndRpcError eXPERIMENTALMaintenanceWindows(jsonRpcRequestForEXPERIMENTALMaintenanceWindows)



[Deprecated] Returns the future windows for maintenance in current epoch for the specified account. In the maintenance windows, the node will not be block producer or chunk producer. Consider using maintenance_windows instead.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = DefaultApi()
val jsonRpcRequestForEXPERIMENTALMaintenanceWindows : JsonRpcRequestForEXPERIMENTALMaintenanceWindows =  // JsonRpcRequestForEXPERIMENTALMaintenanceWindows | 
try {
    val result : JsonRpcResponseForArrayOfRangeOfUint64AndRpcError = apiInstance.eXPERIMENTALMaintenanceWindows(jsonRpcRequestForEXPERIMENTALMaintenanceWindows)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#eXPERIMENTALMaintenanceWindows")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#eXPERIMENTALMaintenanceWindows")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **jsonRpcRequestForEXPERIMENTALMaintenanceWindows** | [**JsonRpcRequestForEXPERIMENTALMaintenanceWindows**](JsonRpcRequestForEXPERIMENTALMaintenanceWindows.md)|  | |

### Return type

[**JsonRpcResponseForArrayOfRangeOfUint64AndRpcError**](JsonRpcResponseForArrayOfRangeOfUint64AndRpcError.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a id="eXPERIMENTALProtocolConfig"></a>
# **eXPERIMENTALProtocolConfig**
> JsonRpcResponseForRpcProtocolConfigResponseAndRpcError eXPERIMENTALProtocolConfig(jsonRpcRequestForEXPERIMENTALProtocolConfig)



A configuration that defines the protocol-level parameters such as gas/storage costs, limits, feature flags, other settings

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = DefaultApi()
val jsonRpcRequestForEXPERIMENTALProtocolConfig : JsonRpcRequestForEXPERIMENTALProtocolConfig =  // JsonRpcRequestForEXPERIMENTALProtocolConfig | 
try {
    val result : JsonRpcResponseForRpcProtocolConfigResponseAndRpcError = apiInstance.eXPERIMENTALProtocolConfig(jsonRpcRequestForEXPERIMENTALProtocolConfig)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#eXPERIMENTALProtocolConfig")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#eXPERIMENTALProtocolConfig")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **jsonRpcRequestForEXPERIMENTALProtocolConfig** | [**JsonRpcRequestForEXPERIMENTALProtocolConfig**](JsonRpcRequestForEXPERIMENTALProtocolConfig.md)|  | |

### Return type

[**JsonRpcResponseForRpcProtocolConfigResponseAndRpcError**](JsonRpcResponseForRpcProtocolConfigResponseAndRpcError.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a id="eXPERIMENTALReceipt"></a>
# **eXPERIMENTALReceipt**
> JsonRpcResponseForRpcReceiptResponseAndRpcError eXPERIMENTALReceipt(jsonRpcRequestForEXPERIMENTALReceipt)



Fetches a receipt by its ID (as is, without a status or execution outcome)

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = DefaultApi()
val jsonRpcRequestForEXPERIMENTALReceipt : JsonRpcRequestForEXPERIMENTALReceipt =  // JsonRpcRequestForEXPERIMENTALReceipt | 
try {
    val result : JsonRpcResponseForRpcReceiptResponseAndRpcError = apiInstance.eXPERIMENTALReceipt(jsonRpcRequestForEXPERIMENTALReceipt)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#eXPERIMENTALReceipt")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#eXPERIMENTALReceipt")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **jsonRpcRequestForEXPERIMENTALReceipt** | [**JsonRpcRequestForEXPERIMENTALReceipt**](JsonRpcRequestForEXPERIMENTALReceipt.md)|  | |

### Return type

[**JsonRpcResponseForRpcReceiptResponseAndRpcError**](JsonRpcResponseForRpcReceiptResponseAndRpcError.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a id="eXPERIMENTALSplitStorageInfo"></a>
# **eXPERIMENTALSplitStorageInfo**
> JsonRpcResponseForRpcSplitStorageInfoResponseAndRpcError eXPERIMENTALSplitStorageInfo(jsonRpcRequestForEXPERIMENTALSplitStorageInfo)



Contains the split storage information. More info on split storage [here](https://near-nodes.io/archival/split-storage-archival)

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = DefaultApi()
val jsonRpcRequestForEXPERIMENTALSplitStorageInfo : JsonRpcRequestForEXPERIMENTALSplitStorageInfo =  // JsonRpcRequestForEXPERIMENTALSplitStorageInfo | 
try {
    val result : JsonRpcResponseForRpcSplitStorageInfoResponseAndRpcError = apiInstance.eXPERIMENTALSplitStorageInfo(jsonRpcRequestForEXPERIMENTALSplitStorageInfo)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#eXPERIMENTALSplitStorageInfo")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#eXPERIMENTALSplitStorageInfo")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **jsonRpcRequestForEXPERIMENTALSplitStorageInfo** | [**JsonRpcRequestForEXPERIMENTALSplitStorageInfo**](JsonRpcRequestForEXPERIMENTALSplitStorageInfo.md)|  | |

### Return type

[**JsonRpcResponseForRpcSplitStorageInfoResponseAndRpcError**](JsonRpcResponseForRpcSplitStorageInfoResponseAndRpcError.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a id="eXPERIMENTALTxStatus"></a>
# **eXPERIMENTALTxStatus**
> JsonRpcResponseForRpcTransactionResponseAndRpcError eXPERIMENTALTxStatus(jsonRpcRequestForEXPERIMENTALTxStatus)



Queries status of a transaction by hash, returning the final transaction result and details of all receipts.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = DefaultApi()
val jsonRpcRequestForEXPERIMENTALTxStatus : JsonRpcRequestForEXPERIMENTALTxStatus =  // JsonRpcRequestForEXPERIMENTALTxStatus | 
try {
    val result : JsonRpcResponseForRpcTransactionResponseAndRpcError = apiInstance.eXPERIMENTALTxStatus(jsonRpcRequestForEXPERIMENTALTxStatus)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#eXPERIMENTALTxStatus")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#eXPERIMENTALTxStatus")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **jsonRpcRequestForEXPERIMENTALTxStatus** | [**JsonRpcRequestForEXPERIMENTALTxStatus**](JsonRpcRequestForEXPERIMENTALTxStatus.md)|  | |

### Return type

[**JsonRpcResponseForRpcTransactionResponseAndRpcError**](JsonRpcResponseForRpcTransactionResponseAndRpcError.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a id="eXPERIMENTALValidatorsOrdered"></a>
# **eXPERIMENTALValidatorsOrdered**
> JsonRpcResponseForArrayOfValidatorStakeViewAndRpcError eXPERIMENTALValidatorsOrdered(jsonRpcRequestForEXPERIMENTALValidatorsOrdered)



Returns the current epoch validators ordered in the block producer order with repetition. This endpoint is solely used for bridge currently and is not intended for other external use cases.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = DefaultApi()
val jsonRpcRequestForEXPERIMENTALValidatorsOrdered : JsonRpcRequestForEXPERIMENTALValidatorsOrdered =  // JsonRpcRequestForEXPERIMENTALValidatorsOrdered | 
try {
    val result : JsonRpcResponseForArrayOfValidatorStakeViewAndRpcError = apiInstance.eXPERIMENTALValidatorsOrdered(jsonRpcRequestForEXPERIMENTALValidatorsOrdered)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#eXPERIMENTALValidatorsOrdered")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#eXPERIMENTALValidatorsOrdered")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **jsonRpcRequestForEXPERIMENTALValidatorsOrdered** | [**JsonRpcRequestForEXPERIMENTALValidatorsOrdered**](JsonRpcRequestForEXPERIMENTALValidatorsOrdered.md)|  | |

### Return type

[**JsonRpcResponseForArrayOfValidatorStakeViewAndRpcError**](JsonRpcResponseForArrayOfValidatorStakeViewAndRpcError.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a id="gasPrice"></a>
# **gasPrice**
> JsonRpcResponseForRpcGasPriceResponseAndRpcError gasPrice(jsonRpcRequestForGasPrice)



Returns gas price for a specific block_height or block_hash. Using [null] will return the most recent block&#39;s gas price.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = DefaultApi()
val jsonRpcRequestForGasPrice : JsonRpcRequestForGasPrice =  // JsonRpcRequestForGasPrice | 
try {
    val result : JsonRpcResponseForRpcGasPriceResponseAndRpcError = apiInstance.gasPrice(jsonRpcRequestForGasPrice)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#gasPrice")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#gasPrice")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **jsonRpcRequestForGasPrice** | [**JsonRpcRequestForGasPrice**](JsonRpcRequestForGasPrice.md)|  | |

### Return type

[**JsonRpcResponseForRpcGasPriceResponseAndRpcError**](JsonRpcResponseForRpcGasPriceResponseAndRpcError.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a id="genesisConfig"></a>
# **genesisConfig**
> JsonRpcResponseForGenesisConfigAndRpcError genesisConfig(jsonRpcRequestForGenesisConfig)



Get initial state and parameters for the genesis block

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = DefaultApi()
val jsonRpcRequestForGenesisConfig : JsonRpcRequestForGenesisConfig =  // JsonRpcRequestForGenesisConfig | 
try {
    val result : JsonRpcResponseForGenesisConfigAndRpcError = apiInstance.genesisConfig(jsonRpcRequestForGenesisConfig)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#genesisConfig")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#genesisConfig")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **jsonRpcRequestForGenesisConfig** | [**JsonRpcRequestForGenesisConfig**](JsonRpcRequestForGenesisConfig.md)|  | |

### Return type

[**JsonRpcResponseForGenesisConfigAndRpcError**](JsonRpcResponseForGenesisConfigAndRpcError.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a id="health"></a>
# **health**
> JsonRpcResponseForNullableRpcHealthResponseAndRpcError health(jsonRpcRequestForHealth)



Returns the current health status of the RPC node the client connects to.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = DefaultApi()
val jsonRpcRequestForHealth : JsonRpcRequestForHealth =  // JsonRpcRequestForHealth | 
try {
    val result : JsonRpcResponseForNullableRpcHealthResponseAndRpcError = apiInstance.health(jsonRpcRequestForHealth)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#health")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#health")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **jsonRpcRequestForHealth** | [**JsonRpcRequestForHealth**](JsonRpcRequestForHealth.md)|  | |

### Return type

[**JsonRpcResponseForNullableRpcHealthResponseAndRpcError**](JsonRpcResponseForNullableRpcHealthResponseAndRpcError.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a id="lightClientProof"></a>
# **lightClientProof**
> JsonRpcResponseForRpcLightClientExecutionProofResponseAndRpcError lightClientProof(jsonRpcRequestForLightClientProof)



Returns the proofs for a transaction execution.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = DefaultApi()
val jsonRpcRequestForLightClientProof : JsonRpcRequestForLightClientProof =  // JsonRpcRequestForLightClientProof | 
try {
    val result : JsonRpcResponseForRpcLightClientExecutionProofResponseAndRpcError = apiInstance.lightClientProof(jsonRpcRequestForLightClientProof)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#lightClientProof")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#lightClientProof")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **jsonRpcRequestForLightClientProof** | [**JsonRpcRequestForLightClientProof**](JsonRpcRequestForLightClientProof.md)|  | |

### Return type

[**JsonRpcResponseForRpcLightClientExecutionProofResponseAndRpcError**](JsonRpcResponseForRpcLightClientExecutionProofResponseAndRpcError.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a id="maintenanceWindows"></a>
# **maintenanceWindows**
> JsonRpcResponseForArrayOfRangeOfUint64AndRpcError maintenanceWindows(jsonRpcRequestForMaintenanceWindows)



Returns the future windows for maintenance in current epoch for the specified account. In the maintenance windows, the node will not be block producer or chunk producer.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = DefaultApi()
val jsonRpcRequestForMaintenanceWindows : JsonRpcRequestForMaintenanceWindows =  // JsonRpcRequestForMaintenanceWindows | 
try {
    val result : JsonRpcResponseForArrayOfRangeOfUint64AndRpcError = apiInstance.maintenanceWindows(jsonRpcRequestForMaintenanceWindows)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#maintenanceWindows")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#maintenanceWindows")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **jsonRpcRequestForMaintenanceWindows** | [**JsonRpcRequestForMaintenanceWindows**](JsonRpcRequestForMaintenanceWindows.md)|  | |

### Return type

[**JsonRpcResponseForArrayOfRangeOfUint64AndRpcError**](JsonRpcResponseForArrayOfRangeOfUint64AndRpcError.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a id="networkInfo"></a>
# **networkInfo**
> JsonRpcResponseForRpcNetworkInfoResponseAndRpcError networkInfo(jsonRpcRequestForNetworkInfo)



Queries the current state of node network connections. This includes information about active peers, transmitted data, known producers, etc.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = DefaultApi()
val jsonRpcRequestForNetworkInfo : JsonRpcRequestForNetworkInfo =  // JsonRpcRequestForNetworkInfo | 
try {
    val result : JsonRpcResponseForRpcNetworkInfoResponseAndRpcError = apiInstance.networkInfo(jsonRpcRequestForNetworkInfo)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#networkInfo")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#networkInfo")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **jsonRpcRequestForNetworkInfo** | [**JsonRpcRequestForNetworkInfo**](JsonRpcRequestForNetworkInfo.md)|  | |

### Return type

[**JsonRpcResponseForRpcNetworkInfoResponseAndRpcError**](JsonRpcResponseForRpcNetworkInfoResponseAndRpcError.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a id="nextLightClientBlock"></a>
# **nextLightClientBlock**
> JsonRpcResponseForRpcLightClientNextBlockResponseAndRpcError nextLightClientBlock(jsonRpcRequestForNextLightClientBlock)



Returns the next light client block.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = DefaultApi()
val jsonRpcRequestForNextLightClientBlock : JsonRpcRequestForNextLightClientBlock =  // JsonRpcRequestForNextLightClientBlock | 
try {
    val result : JsonRpcResponseForRpcLightClientNextBlockResponseAndRpcError = apiInstance.nextLightClientBlock(jsonRpcRequestForNextLightClientBlock)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#nextLightClientBlock")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#nextLightClientBlock")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **jsonRpcRequestForNextLightClientBlock** | [**JsonRpcRequestForNextLightClientBlock**](JsonRpcRequestForNextLightClientBlock.md)|  | |

### Return type

[**JsonRpcResponseForRpcLightClientNextBlockResponseAndRpcError**](JsonRpcResponseForRpcLightClientNextBlockResponseAndRpcError.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a id="query"></a>
# **query**
> JsonRpcResponseForRpcQueryResponseAndRpcError query(jsonRpcRequestForQuery)



This module allows you to make generic requests to the network.  The &#x60;RpcQueryRequest&#x60; struct takes in a [&#x60;BlockReference&#x60;](https://docs.rs/near-primitives/0.12.0/near_primitives/types/enum.BlockReference.html) and a [&#x60;QueryRequest&#x60;](https://docs.rs/near-primitives/0.12.0/near_primitives/views/enum.QueryRequest.html).  The &#x60;BlockReference&#x60; enum allows you to specify a block by &#x60;Finality&#x60;, &#x60;BlockId&#x60; or &#x60;SyncCheckpoint&#x60;.  The &#x60;QueryRequest&#x60; enum provides multiple variants for performing the following actions:  - View an account&#39;s details  - View a contract&#39;s code  - View the state of an account  - View the &#x60;AccessKey&#x60; of an account  - View the &#x60;AccessKeyList&#x60; of an account  - Call a function in a contract deployed on the network.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = DefaultApi()
val jsonRpcRequestForQuery : JsonRpcRequestForQuery =  // JsonRpcRequestForQuery | 
try {
    val result : JsonRpcResponseForRpcQueryResponseAndRpcError = apiInstance.query(jsonRpcRequestForQuery)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#query")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#query")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **jsonRpcRequestForQuery** | [**JsonRpcRequestForQuery**](JsonRpcRequestForQuery.md)|  | |

### Return type

[**JsonRpcResponseForRpcQueryResponseAndRpcError**](JsonRpcResponseForRpcQueryResponseAndRpcError.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a id="sendTx"></a>
# **sendTx**
> JsonRpcResponseForRpcTransactionResponseAndRpcError sendTx(jsonRpcRequestForSendTx)



Sends transaction. Returns the guaranteed execution status and the results the blockchain can provide at the moment.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = DefaultApi()
val jsonRpcRequestForSendTx : JsonRpcRequestForSendTx =  // JsonRpcRequestForSendTx | 
try {
    val result : JsonRpcResponseForRpcTransactionResponseAndRpcError = apiInstance.sendTx(jsonRpcRequestForSendTx)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#sendTx")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#sendTx")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **jsonRpcRequestForSendTx** | [**JsonRpcRequestForSendTx**](JsonRpcRequestForSendTx.md)|  | |

### Return type

[**JsonRpcResponseForRpcTransactionResponseAndRpcError**](JsonRpcResponseForRpcTransactionResponseAndRpcError.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a id="status"></a>
# **status**
> JsonRpcResponseForRpcStatusResponseAndRpcError status(jsonRpcRequestForStatus)



Requests the status of the connected RPC node. This includes information about sync status, nearcore node version, protocol version, the current set of validators, etc.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = DefaultApi()
val jsonRpcRequestForStatus : JsonRpcRequestForStatus =  // JsonRpcRequestForStatus | 
try {
    val result : JsonRpcResponseForRpcStatusResponseAndRpcError = apiInstance.status(jsonRpcRequestForStatus)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#status")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#status")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **jsonRpcRequestForStatus** | [**JsonRpcRequestForStatus**](JsonRpcRequestForStatus.md)|  | |

### Return type

[**JsonRpcResponseForRpcStatusResponseAndRpcError**](JsonRpcResponseForRpcStatusResponseAndRpcError.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a id="tx"></a>
# **tx**
> JsonRpcResponseForRpcTransactionResponseAndRpcError tx(jsonRpcRequestForTx)



Queries status of a transaction by hash and returns the final transaction result.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = DefaultApi()
val jsonRpcRequestForTx : JsonRpcRequestForTx =  // JsonRpcRequestForTx | 
try {
    val result : JsonRpcResponseForRpcTransactionResponseAndRpcError = apiInstance.tx(jsonRpcRequestForTx)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#tx")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#tx")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **jsonRpcRequestForTx** | [**JsonRpcRequestForTx**](JsonRpcRequestForTx.md)|  | |

### Return type

[**JsonRpcResponseForRpcTransactionResponseAndRpcError**](JsonRpcResponseForRpcTransactionResponseAndRpcError.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a id="validators"></a>
# **validators**
> JsonRpcResponseForRpcValidatorResponseAndRpcError validators(jsonRpcRequestForValidators)



Queries active validators on the network. Returns details and the state of validation on the blockchain.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = DefaultApi()
val jsonRpcRequestForValidators : JsonRpcRequestForValidators =  // JsonRpcRequestForValidators | 
try {
    val result : JsonRpcResponseForRpcValidatorResponseAndRpcError = apiInstance.validators(jsonRpcRequestForValidators)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#validators")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#validators")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **jsonRpcRequestForValidators** | [**JsonRpcRequestForValidators**](JsonRpcRequestForValidators.md)|  | |

### Return type

[**JsonRpcResponseForRpcValidatorResponseAndRpcError**](JsonRpcResponseForRpcValidatorResponseAndRpcError.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

