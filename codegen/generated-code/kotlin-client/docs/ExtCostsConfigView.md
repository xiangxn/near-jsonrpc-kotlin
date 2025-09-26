
# ExtCostsConfigView

## Properties
| Name | Type | Description | Notes |
| ------------ | ------------- | ------------- | ------------- |
| **altBn128G1MultiexpBase** | **kotlin.Int** | Base cost for multiexp |  |
| **altBn128G1MultiexpElement** | **kotlin.Int** | Per element cost for multiexp |  |
| **altBn128G1SumBase** | **kotlin.Int** | Base cost for sum |  |
| **altBn128G1SumElement** | **kotlin.Int** | Per element cost for sum |  |
| **altBn128PairingCheckBase** | **kotlin.Int** | Base cost for pairing check |  |
| **altBn128PairingCheckElement** | **kotlin.Int** | Per element cost for pairing check |  |
| **base** | **kotlin.Int** | Base cost for calling a host function. |  |
| **bls12381G1MultiexpBase** | **kotlin.Int** |  |  |
| **bls12381G1MultiexpElement** | **kotlin.Int** |  |  |
| **bls12381G2MultiexpBase** | **kotlin.Int** |  |  |
| **bls12381G2MultiexpElement** | **kotlin.Int** |  |  |
| **bls12381MapFp2ToG2Base** | **kotlin.Int** |  |  |
| **bls12381MapFp2ToG2Element** | **kotlin.Int** |  |  |
| **bls12381MapFpToG1Base** | **kotlin.Int** |  |  |
| **bls12381MapFpToG1Element** | **kotlin.Int** |  |  |
| **bls12381P1DecompressBase** | **kotlin.Int** |  |  |
| **bls12381P1DecompressElement** | **kotlin.Int** |  |  |
| **bls12381P1SumBase** | **kotlin.Int** |  |  |
| **bls12381P1SumElement** | **kotlin.Int** |  |  |
| **bls12381P2DecompressBase** | **kotlin.Int** |  |  |
| **bls12381P2DecompressElement** | **kotlin.Int** |  |  |
| **bls12381P2SumBase** | **kotlin.Int** |  |  |
| **bls12381P2SumElement** | **kotlin.Int** |  |  |
| **bls12381PairingBase** | **kotlin.Int** |  |  |
| **bls12381PairingElement** | **kotlin.Int** |  |  |
| **contractCompileBase** | **kotlin.Int** |  |  |
| **contractCompileBytes** | **kotlin.Int** |  |  |
| **contractLoadingBase** | **kotlin.Int** | Base cost of loading a pre-compiled contract |  |
| **contractLoadingBytes** | **kotlin.Int** | Cost per byte of loading a pre-compiled contract |  |
| **ecrecoverBase** | **kotlin.Int** | Cost of calling ecrecover |  |
| **ed25519VerifyBase** | **kotlin.Int** | Cost of getting ed25519 base |  |
| **ed25519VerifyByte** | **kotlin.Int** | Cost of getting ed25519 per byte |  |
| **keccak256Base** | **kotlin.Int** | Cost of getting sha256 base |  |
| **keccak256Byte** | **kotlin.Int** | Cost of getting sha256 per byte |  |
| **keccak512Base** | **kotlin.Int** | Cost of getting sha256 base |  |
| **keccak512Byte** | **kotlin.Int** | Cost of getting sha256 per byte |  |
| **logBase** | **kotlin.Int** | Cost for calling logging. |  |
| **logByte** | **kotlin.Int** | Cost for logging per byte |  |
| **promiseAndBase** | **kotlin.Int** | Cost for calling &#x60;promise_and&#x60; |  |
| **promiseAndPerPromise** | **kotlin.Int** | Cost for calling &#x60;promise_and&#x60; for each promise |  |
| **promiseReturn** | **kotlin.Int** | Cost for calling &#x60;promise_return&#x60; |  |
| **readCachedTrieNode** | **kotlin.Int** | Cost for reading trie node from memory |  |
| **readMemoryBase** | **kotlin.Int** | Base cost for guest memory read |  |
| **readMemoryByte** | **kotlin.Int** | Cost for guest memory read |  |
| **readRegisterBase** | **kotlin.Int** | Base cost for reading from register |  |
| **readRegisterByte** | **kotlin.Int** | Cost for reading byte from register |  |
| **ripemd160Base** | **kotlin.Int** | Cost of getting ripemd160 base |  |
| **ripemd160Block** | **kotlin.Int** | Cost of getting ripemd160 per message block |  |
| **sha256Base** | **kotlin.Int** | Cost of getting sha256 base |  |
| **sha256Byte** | **kotlin.Int** | Cost of getting sha256 per byte |  |
| **storageHasKeyBase** | **kotlin.Int** | Storage trie check for key existence cost base |  |
| **storageHasKeyByte** | **kotlin.Int** | Storage trie check for key existence per key byte |  |
| **storageIterCreateFromByte** | **kotlin.Int** | Create trie range iterator cost per byte of from key. |  |
| **storageIterCreatePrefixBase** | **kotlin.Int** | Create trie prefix iterator cost base |  |
| **storageIterCreatePrefixByte** | **kotlin.Int** | Create trie prefix iterator cost per byte. |  |
| **storageIterCreateRangeBase** | **kotlin.Int** | Create trie range iterator cost base |  |
| **storageIterCreateToByte** | **kotlin.Int** | Create trie range iterator cost per byte of to key. |  |
| **storageIterNextBase** | **kotlin.Int** | Trie iterator per key base cost |  |
| **storageIterNextKeyByte** | **kotlin.Int** | Trie iterator next key byte cost |  |
| **storageIterNextValueByte** | **kotlin.Int** | Trie iterator next key byte cost |  |
| **storageLargeReadOverheadBase** | **kotlin.Int** | Storage trie read key overhead base cost, when doing large reads |  |
| **storageLargeReadOverheadByte** | **kotlin.Int** | Storage trie read key overhead  per-byte cost, when doing large reads |  |
| **storageReadBase** | **kotlin.Int** | Storage trie read key base cost |  |
| **storageReadKeyByte** | **kotlin.Int** | Storage trie read key per byte cost |  |
| **storageReadValueByte** | **kotlin.Int** | Storage trie read value cost per byte cost |  |
| **storageRemoveBase** | **kotlin.Int** | Remove key from trie base cost |  |
| **storageRemoveKeyByte** | **kotlin.Int** | Remove key from trie per byte cost |  |
| **storageRemoveRetValueByte** | **kotlin.Int** | Remove key from trie ret value byte cost |  |
| **storageWriteBase** | **kotlin.Int** | Storage trie write key base cost |  |
| **storageWriteEvictedByte** | **kotlin.Int** | Storage trie write cost per byte of evicted value. |  |
| **storageWriteKeyByte** | **kotlin.Int** | Storage trie write key per byte cost |  |
| **storageWriteValueByte** | **kotlin.Int** | Storage trie write value per byte cost |  |
| **touchingTrieNode** | **kotlin.Int** | Cost per reading trie node from DB |  |
| **utf16DecodingBase** | **kotlin.Int** | Base cost of decoding utf16. It&#39;s used for &#x60;log_utf16&#x60;. |  |
| **utf16DecodingByte** | **kotlin.Int** | Cost per byte of decoding utf16. It&#39;s used for &#x60;log_utf16&#x60;. |  |
| **utf8DecodingBase** | **kotlin.Int** | Base cost of decoding utf8. It&#39;s used for &#x60;log_utf8&#x60; and &#x60;panic_utf8&#x60;. |  |
| **utf8DecodingByte** | **kotlin.Int** | Cost per byte of decoding utf8. It&#39;s used for &#x60;log_utf8&#x60; and &#x60;panic_utf8&#x60;. |  |
| **validatorStakeBase** | **kotlin.Int** | Cost of calling &#x60;validator_stake&#x60;. |  |
| **validatorTotalStakeBase** | **kotlin.Int** | Cost of calling &#x60;validator_total_stake&#x60;. |  |
| **writeMemoryBase** | **kotlin.Int** | Base cost for guest memory write |  |
| **writeMemoryByte** | **kotlin.Int** | Cost for guest memory write per byte |  |
| **writeRegisterBase** | **kotlin.Int** | Base cost for writing into register |  |
| **writeRegisterByte** | **kotlin.Int** | Cost for writing byte into register |  |
| **yieldCreateBase** | **kotlin.Int** | Base cost for creating a yield promise. |  |
| **yieldCreateByte** | **kotlin.Int** | Per byte cost of arguments and method name. |  |
| **yieldResumeBase** | **kotlin.Int** | Base cost for resuming a yield receipt. |  |
| **yieldResumeByte** | **kotlin.Int** | Per byte cost of resume payload. |  |



