# Near-jsonrpc-kotlin Code Generation Tool

This project is an OpenAPI-based code generation tool with the following features:
- Automatically generates Kotlin code (including handling complex structures like `allOf` and `discriminator`).
- Supports `BigInteger` mapping and property-level serialization annotations.

## Usage
1. Generate code:
   ```bash
   ./gradlew :codegen:run
   ```
2. Build the project:
   ```bash
   ./gradlew build
   ```

## Maintenance Workflow

### CI/CD Automation Process
1. **Daily OpenAPI Spec Check**:
   - CI checks daily for updates to the OpenAPI spec.
2. **Automatic Update and Code Generation**:
   - If the OpenAPI spec changes, it automatically updates the spec and regenerates code and tests.
3. **Test Execution**:
   - After generation, unit tests and integration tests are automatically run.
4. **PR Generation**:
   - Automatically generates a PR to the `master` branch for manual review.
5. **Manual Exception Handling**:
   - If necessary, manually update integration tests or handle generation exceptions.
6. **Publish to Maven Central**:
   - After PR review, merging to `master` automatically publishes to Maven Central.