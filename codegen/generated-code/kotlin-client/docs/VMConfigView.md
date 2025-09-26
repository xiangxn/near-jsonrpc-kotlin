
# VMConfigView

## Properties
| Name | Type | Description | Notes |
| ------------ | ------------- | ------------- | ------------- |
| **deterministicAccountIds** | **kotlin.Boolean** | See [VMConfig::deterministic_account_ids](crate::vm::Config::deterministic_account_ids). |  |
| **discardCustomSections** | **kotlin.Boolean** | See [VMConfig::discard_custom_sections](crate::vm::Config::discard_custom_sections). |  |
| **ethImplicitAccounts** | **kotlin.Boolean** | See [VMConfig::eth_implicit_accounts](crate::vm::Config::eth_implicit_accounts). |  |
| **extCosts** | [**ExtCostsConfigView**](ExtCostsConfigView.md) | Costs for runtime externals |  |
| **fixContractLoadingCost** | **kotlin.Boolean** | See [VMConfig::fix_contract_loading_cost](crate::vm::Config::fix_contract_loading_cost). |  |
| **globalContractHostFns** | **kotlin.Boolean** | See [VMConfig::global_contract_host_fns](crate::vm::Config::global_contract_host_fns). |  |
| **growMemCost** | **kotlin.Int** | Gas cost of a growing memory by single page. |  |
| **implicitAccountCreation** | **kotlin.Boolean** | See [VMConfig::implicit_account_creation](crate::vm::Config::implicit_account_creation). |  |
| **limitConfig** | [**LimitConfig**](LimitConfig.md) | Describes limits for VM and Runtime.  TODO: Consider changing this to &#x60;VMLimitConfigView&#x60; to avoid dependency on runtime. |  |
| **reftypesBulkMemory** | **kotlin.Boolean** | See [VMConfig::reftypes_bulk_memory](crate::vm::Config::reftypes_bulk_memory). |  |
| **regularOpCost** | **kotlin.Int** | Gas cost of a regular operation. |  |
| **saturatingFloatToInt** | **kotlin.Boolean** | See [VMConfig::saturating_float_to_int](crate::vm::Config::saturating_float_to_int). |  |
| **storageGetMode** | [**StorageGetMode**](StorageGetMode.md) | See [VMConfig::storage_get_mode](crate::vm::Config::storage_get_mode). |  |
| **vmKind** | [**VMKind**](VMKind.md) | See [VMConfig::vm_kind](crate::vm::Config::vm_kind). |  |



