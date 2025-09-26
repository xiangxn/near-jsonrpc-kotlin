
# LimitConfig

## Properties
| Name | Type | Description | Notes |
| ------------ | ------------- | ------------- | ------------- |
| **initialMemoryPages** | **kotlin.Int** | The initial number of memory pages. NOTE: It&#39;s not a limiter itself, but it&#39;s a value we use for initial_memory_pages. |  |
| **maxActionsPerReceipt** | **kotlin.Int** | Max number of actions per receipt. |  |
| **maxArgumentsLength** | **kotlin.Int** | Max length of arguments in a function call action. |  |
| **maxContractSize** | **kotlin.Int** | Max contract size |  |
| **maxGasBurnt** | **kotlin.Int** | Max amount of gas that can be used, excluding gas attached to promises. |  |
| **maxLengthMethodName** | **kotlin.Int** | Max length of any method name (without terminating character). |  |
| **maxLengthReturnedData** | **kotlin.Int** | Max length of returned data |  |
| **maxLengthStorageKey** | **kotlin.Int** | Max storage key size |  |
| **maxLengthStorageValue** | **kotlin.Int** | Max storage value size |  |
| **maxMemoryPages** | **kotlin.Int** | What is the maximal memory pages amount is allowed to have for a contract. |  |
| **maxNumberBytesMethodNames** | **kotlin.Int** | Max total length of all method names (including terminating character) for a function call permission access key. |  |
| **maxNumberInputDataDependencies** | **kotlin.Int** | Max number of input data dependencies |  |
| **maxNumberLogs** | **kotlin.Int** | Maximum number of log entries. |  |
| **maxNumberRegisters** | **kotlin.Int** | Maximum number of registers that can be used simultaneously.  Note that due to an implementation quirk [read: a bug] in VMLogic, if we have this number of registers, no subsequent writes to the registers will succeed even if they replace an existing register. |  |
| **maxPromisesPerFunctionCallAction** | **kotlin.Int** | Max number of promises that a function call can create |  |
| **maxReceiptSize** | **kotlin.Int** | Max receipt size |  |
| **maxRegisterSize** | **kotlin.Int** | Maximum number of bytes that can be stored in a single register. |  |
| **maxStackHeight** | **kotlin.Int** | How tall the stack is allowed to grow?  See &lt;https://wiki.parity.io/WebAssembly-StackHeight&gt; to find out how the stack frame cost is calculated. |  |
| **maxTotalLogLength** | **kotlin.Int** | Maximum total length in bytes of all log messages. |  |
| **maxTotalPrepaidGas** | **kotlin.Int** | Max total prepaid gas for all function call actions per receipt. |  |
| **maxTransactionSize** | **kotlin.Int** | Max transaction size |  |
| **maxYieldPayloadSize** | **kotlin.Int** | Maximum number of bytes for payload passed over a yield resume. |  |
| **perReceiptStorageProofSizeLimit** | **kotlin.Int** | Hard limit on the size of storage proof generated while executing a single receipt. |  |
| **registersMemoryLimit** | **kotlin.Int** | Limit of memory used by registers. |  |
| **yieldTimeoutLengthInBlocks** | **kotlin.Int** | Number of blocks after which a yielded promise times out. |  |
| **accountIdValidityRulesVersion** | **kotlin.Int** | Whether to enforce account_id well-formed-ness where it wasn&#39;t enforced historically. |  [optional] |
| **maxElementsPerContractTable** | **kotlin.Int** | If present, stores max number of elements in a single contract&#39;s table |  [optional] |
| **maxFunctionsNumberPerContract** | **kotlin.Int** | If present, stores max number of functions in one contract |  [optional] |
| **maxLocalsPerContract** | **kotlin.Int** | If present, stores max number of locals declared globally in one contract |  [optional] |
| **maxTablesPerContract** | **kotlin.Int** | If present, stores max number of tables declared globally in one contract |  [optional] |



