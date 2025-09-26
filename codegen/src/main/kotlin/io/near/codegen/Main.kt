package io.near.codegen

import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import io.swagger.v3.oas.models.media.Schema
import kotlinx.serialization.json.*
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.math.BigInteger
import org.jetbrains.annotations.Nullable
import org.openapitools.codegen.DefaultGenerator
import org.openapitools.codegen.languages.KotlinClientCodegen
import org.openapitools.codegen.config.CodegenConfigurator
import org.openapitools.codegen.model.ModelsMap
import org.openapitools.codegen.CodegenModel
import org.openapitools.codegen.CodegenProperty


/**
 * 重构后的代码生成器主类
 * 主要改进：
 * 1. 模块化拆分
 * 2. 减少重复代码
 * 3. 增强可读性和维护性
 */
object Main {
    // 常量定义
    public const val TYPES_PKG = "io.near.jsonrpc.types"
    public const val CLIENT_PKG = "io.near.jsonrpc.client"
    private val DEFAULT_SPEC_URL = "https://raw.githubusercontent.com/near/nearcore/master/chain/jsonrpc/openapi/openapi.json"

    // 核心生成器组件
    private val typeGenerator = TypeGenerator()
    private val apiClientGenerator = ApiClientGenerator()
    private val testGenerator = TestGenerator()

    @JvmStatic
    fun main(args: Array<String>) {
        val specUrl = args.getOrElse(0) { DEFAULT_SPEC_URL }
        println("Fetching OpenAPI spec from: $specUrl")

        try {
            // 1. 加载和解析 OpenAPI 规范
            val root = OpenApiParser.fetchSpec(specUrl)
            val components = root["components"]?.jsonObject ?: JsonObject(emptyMap())
            val schemas = components["schemas"]?.jsonObject ?: JsonObject(emptyMap())
            val paths = root["paths"]?.jsonObject ?: JsonObject(emptyMap())

            // 2. 生成数据模型
            typeGenerator.generateModels(specUrl)

            // 3. 生成客户端 API
            apiClientGenerator.generateApiClient(paths)

            // 4. 生成单元测试
            testGenerator.generateTests(schemas)

            // 5. 格式化代码
            CodeFormatter.formatWithSpotless()

            println("✅ Generation completed successfully")
        } catch (e: Exception) {
            System.err.println("❌ Generation failed: ${e.message}")
            e.printStackTrace()
        }
    }
}

/** OpenAPI 规范解析器 */
object OpenApiParser {
    fun fetchSpec(urlOrPath: String): JsonObject {
        return if (urlOrPath.startsWith("http") || urlOrPath.startsWith("https")) {
            fetchRemoteSpec(urlOrPath)
        } else {
            fetchLocalSpec(urlOrPath)
        }
    }

    private fun fetchRemoteSpec(url: String): JsonObject {
        val client = OkHttpClient()
        val req = Request.Builder().url(url).build()
        client.newCall(req).execute().use { resp ->
            val body = resp.body?.string() ?: throw RuntimeException("Empty response from spec URL")
            return Json.parseToJsonElement(body).jsonObject
        }
    }

    private fun fetchLocalSpec(path: String): JsonObject {
        val file = if (path.startsWith(".") || path.startsWith("/")) {
            File(path)
        } else {
            File(System.getProperty("user.dir"), path)
        }
        return Json.parseToJsonElement(file.readText()).jsonObject
    }
}

class NearClientCodegen : KotlinClientCodegen() {
    
    override fun getName(): String = "near-kotlin"

    override fun fromProperty(name: String?, schema: Schema<*>, required: Boolean, schemaIsFromAdditionalProperties: Boolean): CodegenProperty {
        val prop = super.fromProperty(name, schema, required, schemaIsFromAdditionalProperties)
        // if(prop.dataFormat == "uint128"){
        //     println("DEBUG: ${prop.name} is uint128, dataType: ${prop.dataType} ${schema.format}")
        // }
        when (schema.format) {
            "uint128", "int128" -> {
                // println("DEBUG: ${prop.name} is uint128, dataType: ${prop.dataType}")
                prop.datatype = "BigInteger"
                prop.baseType = "BigInteger"
                prop.datatypeWithEnum = "BigInteger"
                prop.vendorExtensions["x-is-biginteger"] = true
                importMapping()["BigInteger"] = "java.math.BigInteger"
            }
            "uint64" -> {
                prop.datatype = "ULong"
                prop.baseType = "ULong"
                prop.datatypeWithEnum = "ULong"
                prop.vendorExtensions["x-is-ulong"] = true
                importMapping()["ULong"] = "kotlin.ULong"
            }
            "uint32", "uint16", "uint8" -> {
                prop.datatype = "UInt"
                prop.baseType = "UInt"
                prop.datatypeWithEnum = "UInt"
                prop.vendorExtensions["x-is-uint"] = true
                importMapping()["UInt"] = "kotlin.UInt"
            }
        }
        return prop
    }
}


/** 类型生成器 */
class TypeGenerator {
    fun generateModels(specUrl: String) {
        val targetDir = File("../near-jsonrpc-types/src/main/kotlin/io/near/jsonrpc/types")
        targetDir.deleteRecursively()
        

        val tempDir = File("build/tmp/openapi-gen")
        tempDir.mkdirs()

        val configurator = CodegenConfigurator().apply {
            setGeneratorName("near-kotlin")
            setInputSpec(specUrl)
            setOutputDir(tempDir.absolutePath)
            setApiPackage("io.near.jsonrpc.client")
            setModelPackage("io.near.jsonrpc.types")

            // 类型映射
            setTypeMappings(mapOf(
                "integer" to "kotlin.Int"
            ))

            setImportMappings(mapOf(
                "BigInteger" to "java.math.BigInteger",
                "ULong" to "kotlin.ULong",
                "UInt" to "kotlin.UInt",
                "BigIntegerSerializer" to "io.near.jsonrpc.serializer.BigIntegerSerializer",
                "ULongSerializer" to "io.near.jsonrpc.serializer.ULongSerializer",
                "ULongListSerializer" to "io.near.jsonrpc.serializer.ULongListSerializer",
                "UIntSerializer" to "io.near.jsonrpc.serializer.UIntSerializer",
                "UIntListSerializer" to "io.near.jsonrpc.serializer.UIntListSerializer"
            ))

            // 只生成 models
            setAdditionalProperties(
                mapOf(
                    "generateModels" to true,
                    "generateApis" to false,
                    "generateSupportingFiles" to false,
                    "serializationLibrary" to "kotlinx_serialization"
                )
            )

            setTemplateDir("openapi-templates")
            setValidateSpec(false)
        }


        
        val generator = DefaultGenerator()
        generator.opts(configurator.toClientOptInput())
        generator.generate()

        val generatedModels = File(tempDir, "src/main/kotlin/io/near/jsonrpc/types")
        replaceEmptyEnums(generatedModels, listOf(
            "GenesisConfigRequest",
            "RpcClientConfigRequest",
            "RpcHealthRequest",
            "RpcHealthResponse",
            "RpcNetworkInfoRequest",
            "RpcStatusRequest"
        ))
        generatedModels.copyRecursively(targetDir, overwrite = true)
        tempDir.deleteRecursively()
    }

    private fun replaceEmptyEnums(generatedModels: File, emptyEnumFiles: List<String>) {
        generatedModels.walkTopDown()
            .filter { it.isFile && it.nameWithoutExtension in emptyEnumFiles }
            .forEach { file ->
                val className = file.nameWithoutExtension
                file.writeText(
                    """
                    package io.near.jsonrpc.types

                    typealias $className = String?
                    """.trimIndent(),
                    Charsets.UTF_8
                )
            }
    }

}

/** API 客户端生成器 */
class ApiClientGenerator {
    fun generateApiClient(paths: JsonObject) {
        val apiFile = FileSpec.builder(Main.CLIENT_PKG, "NearJsonRpcApi")
        val clientClass = TypeSpec.classBuilder("NearJsonRpcApi")
            .addModifiers(KModifier.PUBLIC)
            .primaryConstructor(
                FunSpec.constructorBuilder()
                    .addParameter(
                        ParameterSpec.builder("rpcClient", 
                            ClassName("io.near.jsonrpc.client", "JsonRpcClient")
                        ).build()
                    )
                    .build()
            )
            .addProperty(
                PropertySpec.builder("rpcClient", 
                    ClassName("io.near.jsonrpc.client", "JsonRpcClient")
                ).initializer("rpcClient").build()
            )

        paths.forEach { (path, pathItem) ->
            pathItem.jsonObject.forEach { (httpMethod, operation) ->
                generateApiMethod(
                    operation.jsonObject, 
                    clientClass,
                    path.substringAfterLast('/')
                )
            }
        }

        apiFile.addType(clientClass.build())
        File("../near-jsonrpc-client/src/main/kotlin").mkdirs()
        apiFile.build().writeTo(File("../near-jsonrpc-client/src/main/kotlin"))
    }

    private fun generateApiMethod(
        operation: JsonObject,
        clientClass: TypeSpec.Builder,
        defaultMethodName: String
    ) {
        val operationId = operation["operationId"]?.jsonPrimitive?.content 
            ?: defaultMethodName
        val methodName = StringUtils.camelCase(operationId)

        // 确定返回类型
        val returnType = operation["responses"]?.jsonObject
            ?.get("200")?.jsonObject
            ?.get("content")?.jsonObject
            ?.get("application/json")?.jsonObject
            ?.get("schema")?.let { schema ->
                schema.jsonObject["\$ref"]?.jsonPrimitive?.content?.let { ref ->
                    ClassName(Main.TYPES_PKG, StringUtils.pascalCase(ref.substringAfterLast("/")))
                } ?: ClassName("kotlinx.serialization.json", "JsonElement")
            } ?: ClassName("kotlinx.serialization.json", "JsonElement")

        // 确定参数类型
        val paramType = operation["requestBody"]?.jsonObject
            ?.get("content")?.jsonObject
            ?.get("application/json")?.jsonObject
            ?.get("schema")?.let { schema ->
                schema.jsonObject["\$ref"]?.jsonPrimitive?.content?.let { ref ->
                    ClassName(Main.TYPES_PKG, StringUtils.pascalCase(ref.substringAfterLast("/")))
                } ?: ClassName("kotlinx.serialization.json", "JsonElement").copy(nullable = true)
            } ?: ClassName("kotlinx.serialization.json", "JsonElement").copy(nullable = true)

        val funSpec = FunSpec.builder(methodName)
            .addModifiers(KModifier.SUSPEND)
            .addParameter("params", paramType)
            .returns(returnType)
            .addStatement(
                "return rpcClient.call<%T>(%S, params)",
                returnType, 
                operationId
            )

        // 添加方法文档
        operation["description"]?.jsonPrimitive?.content?.let { desc ->
            funSpec.addKdoc("%L\n", desc)
        }

        clientClass.addFunction(funSpec.build())
    }
}

/** 测试生成器 */
class TestGenerator {
    fun generateTests(schemas: JsonObject) {
        val testDir = File("../near-jsonrpc-types/src/test/kotlin")
        testDir.mkdirs()

        schemas.forEach { (name, schema) ->
            try {
                generateTest(name, schema.jsonObject, testDir)
            } catch (e: Exception) {
                System.err.println("Error generating test for $name: ${e.message}")
            }
        }
    }

    private fun generateTest(
        schemaName: String,
        schema: JsonObject,
        testDir: File
    ) {
        val className = StringUtils.pascalCase(schemaName) + "Test"
        val testFile = FileSpec.builder(Main.TYPES_PKG, className)
            .addImport("org.junit.jupiter.api", "Test")
            .addImport("org.junit.jupiter.api", "TestInstance")
            .addImport("org.junit.jupiter.api.Assertions", "assertNotNull")
            .addImport("org.junit.jupiter.api.Assertions", "assertTrue")

        val testClass = TypeSpec.classBuilder(className)
            .addAnnotation(
                AnnotationSpec.builder(ClassName("org.junit.jupiter.api", "TestInstance"))
                    .addMember("value = %T.Lifecycle.%L", 
                        ClassName("org.junit.jupiter.api", "TestInstance"), 
                        "PER_CLASS")
                    .build()
            )
            .addProperty(
                PropertySpec.builder("json", Json::class.asClassName())
                    .addModifiers(KModifier.PRIVATE)
                    .initializer("%T.Default", Json::class.asClassName())
                    .build()
            )

        // 生成序列化测试
        generateSerializationTest(schemaName, schema, testClass)
        // 生成反序列化测试
        generateDeserializationTest(schemaName, schema, testClass)
        // 生成验证测试
        generateValidationTest(schemaName, schema, testClass)

        testFile.addType(testClass.build())
        testFile.build().writeTo(testDir)
    }

    private fun generateSerializationTest(
        schemaName: String,
        schema: JsonObject,
        testClass: TypeSpec.Builder
    ) {
        val testName = "test" + StringUtils.pascalCase(schemaName) + "Serialization"
        val modelClass = ClassName(Main.TYPES_PKG, StringUtils.pascalCase(schemaName))
        val constructorParams = generateConstructorParams(schema)

        val testMethod = FunSpec.builder(testName)
            .addAnnotation(ClassName("org.junit.jupiter.api", "Test"))
            .addStatement("val instance = %T($constructorParams)", modelClass)
            .addStatement("val jsonStr = json.encodeToString(instance)")
            .addStatement("assertNotNull(jsonStr)")
            .addStatement("assertTrue(jsonStr.isNotBlank())")
            .build()

        testClass.addFunction(testMethod)
    }

    private fun generateConstructorParams(schema: JsonObject): String {
        val properties = schema["properties"]?.jsonObject ?: JsonObject(emptyMap())
        val requiredSet = schema["required"]?.jsonArray?.map { it.jsonPrimitive.content }?.toSet() ?: emptySet()

        return properties.entries.joinToString(", ") { (propName, propSchema) ->
            val propType = when (propSchema.jsonObject["type"]?.jsonPrimitive?.content) {
                "string" -> "\"mock_$propName\""
                "integer" -> "1"
                "boolean" -> "true"
                else -> "null"
            }
            "$propName = $propType"
        }
    }



    private fun generateDeserializationTest(
        schemaName: String,
        schema: JsonObject,
        testClass: TypeSpec.Builder
    ) {
        val testName = "test" + StringUtils.pascalCase(schemaName) + "Deserialization"
        val modelClass = ClassName(Main.TYPES_PKG, StringUtils.pascalCase(schemaName))

        val testMethod = FunSpec.builder(testName)
            .addAnnotation(ClassName("org.junit.jupiter.api", "Test"))
            .addStatement("val jsonStr = %S", "{}")
            .addStatement("val instance = json.decodeFromString<%T>(jsonStr)", modelClass)
            .addStatement("assertNotNull(instance)")
            .build()

        testClass.addFunction(testMethod)
    }

    private fun generateValidationTest(
        schemaName: String,
        schema: JsonObject,
        testClass: TypeSpec.Builder
    ) {
        val requiredProps = schema["required"]?.jsonArray?.map { it.jsonPrimitive.content } ?: emptyList()
        if (requiredProps.isNotEmpty()) {
            val testName = "test" + StringUtils.pascalCase(schemaName) + "Validation"
            val modelClass = ClassName(Main.TYPES_PKG, StringUtils.pascalCase(schemaName))

            val testMethod = FunSpec.builder(testName)
                .addAnnotation(ClassName("org.junit.jupiter.api", "Test"))
                .apply {
                    requiredProps.forEach { prop ->
                        addStatement(
                            "assertThrows<%T> { json.decodeFromString<%T>(%S) }",
                            ClassName("kotlinx.serialization", "SerializationException"),
                            modelClass,
                            "{\"${'$'}prop\":null}"
                        )
                    }
                }
                .build()

            testClass.addFunction(testMethod)
        }
    }
}

/** 代码格式化工具 */
object CodeFormatter {
    fun formatWithSpotless() {
        try {
            ProcessBuilder("gradle", "spotlessApply", "--quiet")
                .directory(File("../"))
                .inheritIO()
                .start()
                .waitFor()
        } catch (e: Exception) {
            println("Warning: Spotless formatting failed: ${e.message}")
        }
    }
}

/** 字符串工具类 */
object StringUtils {
    fun pascalCase(s: String): String = s.split(Regex("[^A-Za-z0-9]+"))
        .filter { it.isNotBlank() }
        .joinToString("") { it.replaceFirstChar { c -> c.uppercase() } }

    fun camelCase(s: String): String {
        // 先按非字母数字分割
        val parts = s.split(Regex("[^A-Za-z0-9]+"))
            .flatMap { part -> 
                // 再拆驼峰单词
                part.split(Regex("(?<=[a-z])(?=[A-Z])|(?<=[A-Z])(?=[A-Z][a-z])"))
            }
            .filter { it.isNotBlank() }

        return parts.mapIndexed { i, p ->
            if (i == 0) p.lowercase() else p.replaceFirstChar { it.uppercase() }
        }.joinToString("")
    }

}