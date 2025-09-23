package io.near.codegen

import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import kotlinx.serialization.json.*
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.math.BigInteger
import org.jetbrains.annotations.Nullable



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
            typeGenerator.generateModels(schemas, components)

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

/** 类型生成器 */
class TypeGenerator {
    fun generateModels(schemas: JsonObject, components: JsonObject) {
        val modelsFile = FileSpec.builder(Main.TYPES_PKG, "NearModels")
        val parentMap = buildParentMap(schemas)
        
        // Handle special cases based on schema rules
        schemas.forEach { (name, schemaEl) ->
            val schema = schemaEl.jsonObject
            val type = schema["type"]?.jsonPrimitive?.contentOrNull
            val format = schema["format"]?.jsonPrimitive?.contentOrNull
            val description = schema["description"]?.jsonPrimitive?.contentOrNull
            val properties = schema["properties"]?.jsonObject ?: JsonObject(emptyMap())


            // Rule 0: If schema has only "type" field, generate a typealias
            if ((schema.size == 1 && type != null) || (schema.size == 2 && type != null && description != null)) {
                if (type == "object") {
                    modelsFile.addType(
                        TypeSpec.interfaceBuilder(StringUtils.pascalCase(name))
                            .addModifiers(KModifier.PUBLIC)
                            .build()
                    )
                } else {
                    modelsFile.addTypeAlias(
                        TypeAliasSpec.builder(StringUtils.pascalCase(name), ClassName("kotlin", type?.replaceFirstChar { it.uppercase() } ?: "Any" ))
                            .addModifiers(KModifier.PUBLIC)
                            .addKdoc(description ?: "")
                            .build()
                    )
                }
            }
            // Rule 1: If it's a primitive type with constraints, generate a wrapper class
            else if (type == "integer" && format == "uint64" && schema["minimum"] != null) {
                modelsFile.addType(
                    TypeSpec.classBuilder(StringUtils.pascalCase(name))
                        .addModifiers(KModifier.PUBLIC)
                        .addAnnotation(AnnotationSpec.builder(ClassName("kotlinx.serialization", "Serializable")).build())
                        .addKdoc(description ?: "")
                        .primaryConstructor(
                            FunSpec.constructorBuilder()
                                .addParameter("value", ClassName("kotlin", "ULong"))
                                .addStatement("require(value >= 0UL) { \"${name} must be a non-negative uint64\" }")
                                .build()
                        )
                        .addProperty(
                            PropertySpec.builder("value", ClassName("kotlin", "ULong"))
                                .initializer("value")
                                .build()
                        )
                        .build()
                )
            }
            else if (properties.isEmpty() && type == "object") {
                modelsFile.addType(
                    TypeSpec.interfaceBuilder(StringUtils.pascalCase(name))
                        .addModifiers(KModifier.PUBLIC)
                        .build()
                )
            } else {
                try {
                    generateModel(name, schema, components, parentMap, modelsFile)
                } catch (e: Exception) {
                    System.err.println("Error generating model $name: ${e.message}")
                }
            }
        }

        // 写入文件
        File("../near-jsonrpc-types/src/main/kotlin").mkdirs()
        modelsFile.build().writeTo(File("../near-jsonrpc-types/src/main/kotlin"))
    }

    private fun resolveType(
        propSchema: JsonElement,
        components: JsonObject
    ): TypeName {
        val schema = if (propSchema is JsonObject) propSchema else JsonObject(emptyMap())
        return when (val type = schema["type"]?.jsonPrimitive?.contentOrNull) {
            "string" -> STRING
            "integer" -> LONG
            "number" -> DOUBLE
            "boolean" -> BOOLEAN
            "array" -> {
                val itemsType = schema["items"]?.let { resolveType(it, components) } ?: STRING
                LIST.parameterizedBy(itemsType)
            }
            "object" -> ClassName("kotlinx.serialization.json", "JsonObject")
            else -> {
                schema["\$ref"]?.jsonPrimitive?.contentOrNull?.let { ref ->
                    val refName = StringUtils.pascalCase(ref.substringAfterLast("/"))
                    ClassName(Main.TYPES_PKG, refName)
                } ?: STRING
            }
        }
    }


    private fun buildParentMap(schemas: JsonObject): MutableMap<String, MutableList<String>> {
        val parentMap = mutableMapOf<String, MutableList<String>>()
        schemas.forEach { (name, schemaEl) ->
            val schema = schemaEl.jsonObject
            // 处理 oneOf 关系
            schema["oneOf"]?.jsonArray?.forEach { variantEl ->
                variantEl.jsonObject["\$ref"]?.jsonPrimitive?.contentOrNull?.let { ref ->
                    val childName = ref.substringAfterLast("/")
                    parentMap.getOrPut(childName) { mutableListOf() }.add(name)
                }
            }
            // 处理 discriminator.mapping 关系
            schema["discriminator"]?.jsonObject?.get("mapping")?.jsonObject?.forEach { (_, refEl) ->
                refEl.jsonPrimitive.contentOrNull?.let { ref ->
                    val childName = ref.substringAfterLast("/")
                    parentMap.getOrPut(childName) { mutableListOf() }.add(name)
                }
            }
        }
        return parentMap
    }

    private fun generateModel(
        name: String,
        schema: JsonObject,
        components: JsonObject,
        parentMap: MutableMap<String, MutableList<String>>,
        modelsFile: FileSpec.Builder
    ) {
        val className = StringUtils.pascalCase(name)
        val topLevelEnum = schema["enum"] as? JsonArray
        
        if (topLevelEnum != null && topLevelEnum.isNotEmpty()) {
            val enumSpec = generateEnumClass(className, schema)
            modelsFile.addType(enumSpec)
            return
        }

        val hasOneOf = schema["oneOf"] != null
        val hasAnyOf = schema["anyOf"] != null
        if (hasOneOf || hasAnyOf) {
            if (schema["properties"] == null) {
                val interfaceSpec = TypeSpec.interfaceBuilder(className)
                    .addModifiers(KModifier.SEALED)
                    .addAnnotation(AnnotationSpec.builder(ClassName("kotlinx.serialization", "Serializable")).build())
                    .build()
                modelsFile.addType(interfaceSpec)
            } else {
                generateAbstractClass(className, schema, parentMap, modelsFile)
            }
            
            generateOneOfVariants(name, schema, modelsFile, Main.TYPES_PKG, components, parentMap)
        } else {
            val hasAllOf = schema["allOf"] != null
            val properties = mergeProperties(schema, components)
            // println("properties: ${className} ===== ${properties.keys}")
            if (properties.isNotEmpty()) {
                modelsFile.addType(generateDataClass(className, schema, properties, parentMap))
            } else if (hasAllOf) {
                println("className: ${className}")
                modelsFile.addType(generateAllOfType(className, schema, parentMap, components))
            }
        }
    }

    private fun generateOneOfVariants(
        name: String,
        schema: JsonObject,
        modelsFile: FileSpec.Builder,
        pkg: String,
        components: JsonObject,
        parentMap: MutableMap<String, MutableList<String>>
    ) {
        val variants = schema["oneOf"]?.jsonArray ?: schema["anyOf"]?.jsonArray ?: return
        val className = StringUtils.pascalCase(name)

        variants.forEachIndexed { index, variantEl ->
            val variant = variantEl.jsonObject
            val ref = variant["\$ref"]?.jsonPrimitive?.contentOrNull
            val title = variant["title"]?.jsonPrimitive?.contentOrNull ?: "Variant${index + 1}"
            val properties = variant["properties"]?.jsonObject
            val requiredFields = variant["required"]?.jsonArray?.map { it.jsonPrimitive.content } ?: emptyList()

            if (ref != null) {
                val childName = ref.substringAfterLast("/")
                parentMap.getOrPut(childName) { mutableListOf() }.add(className)
            } else if (properties != null) {
                val baseProperties = schema.get("properties")?.jsonObject ?: JsonObject(emptyMap())
                val baseRequireds = schema.get("required")?.jsonArray?.map { it.jsonPrimitive.content } ?: emptyList()
                val variantClassName = "${className}${StringUtils.pascalCase(title)}"
                val typeSpec = TypeSpec.classBuilder(variantClassName)
                    .addModifiers(KModifier.DATA)
                    .addAnnotation(AnnotationSpec.builder(ClassName("kotlinx.serialization", "Serializable")).build())
                    .apply {
                        val constructorBuilder = FunSpec.constructorBuilder()

                        // 1. 添加基类参数到构造函数
                        if (baseProperties.isNotEmpty()) {
                            superclass(ClassName(pkg, className))
                            baseProperties.forEach { (propName, propSchema) ->
                                if (propName in baseRequireds) {
                                    val propKotlinName = StringUtils.camelCase(propName)
                                    val propType = resolveType(propSchema, components)
                                    addProperty(
                                        PropertySpec.builder(propKotlinName, propType)
                                            .initializer(propKotlinName)
                                            .addModifiers(KModifier.OVERRIDE, KModifier.PUBLIC)
                                            .build()
                                    )
                                    constructorBuilder.addParameter(
                                        ParameterSpec.builder(propKotlinName, propType).build()
                                    )
                                }
                            }
                        } else {
                            addSuperinterface(ClassName(pkg, className))
                        }
                        // 2. 添加子类特有属性
                        properties.forEach { (propName, propSchema) ->
                            val propKotlinName = StringUtils.camelCase(propName)
                            val propType = resolveType(propSchema, components)
                            val isRequired = propName in requiredFields
                            val obj = propSchema.jsonObject
                            val enum = obj["enum"] as? JsonArray

                            if (enum == null) {
                                addProperty(
                                    PropertySpec.builder(propKotlinName, propType)
                                        .initializer(propKotlinName)
                                        .addModifiers(KModifier.PUBLIC)
                                        .apply { 
                                            if (!isRequired) addAnnotation(Nullable::class) 

                                            if (propKotlinName != propName) {
                                                addAnnotation(
                                                    AnnotationSpec.builder(ClassName("kotlinx.serialization", "SerialName"))
                                                        .addMember("%S", propName)
                                                        .build()
                                                )
                                            }
                                        }
                                        .build()
                                )
                                constructorBuilder.addParameter(
                                    ParameterSpec.builder(propKotlinName, propType).build()
                                )
                            } else {
                                val names = enum.map { it.jsonPrimitive.content }
                                addProperty(
                                    PropertySpec.builder(propKotlinName, propType)
                                        .initializer(propKotlinName)
                                        .apply { 
                                            if (!isRequired) addAnnotation(Nullable::class) 

                                            if (propKotlinName != propName) {
                                                addAnnotation(
                                                    AnnotationSpec.builder(ClassName("kotlinx.serialization", "SerialName"))
                                                        .addMember("%S", propName)
                                                        .build()
                                                )
                                            }
                                        }
                                        .build()
                                )
                                constructorBuilder.addParameter(
                                    ParameterSpec.builder(propKotlinName, propType)
                                    .defaultValue("%S", names.first())
                                    .build()
                                )
                            }
                        }
                        primaryConstructor(constructorBuilder.build())
                    }
                    .build()
                modelsFile.addType(typeSpec)
            }
        }
    }

    private fun generateAbstractClass(
        className: String,
        schema: JsonObject,
        parentMap: MutableMap<String, MutableList<String>>,
        modelsFile: FileSpec.Builder
    ) {
        val properties = schema["properties"]?.jsonObject ?: JsonObject(emptyMap())
        val classBuilder = TypeSpec.classBuilder(className)
            .addModifiers(KModifier.SEALED)
            .addAnnotation(AnnotationSpec.builder(ClassName("kotlinx.serialization", "Serializable")).build())

        val requiredSet = schema["required"]?.jsonArray?.map { it.jsonPrimitive.content }?.toSet() ?: emptySet()

        properties.forEach { (propName, propSchema) ->
            val propKotlinName = StringUtils.camelCase(propName)
            val propType = mapType(propSchema, Main.TYPES_PKG)
            val isRequired = requiredSet.contains(propName)
            if (isRequired) {
                val propBuilder = PropertySpec.builder(propKotlinName, propType)
                
                if (propName != propKotlinName) {
                    propBuilder.addAnnotation(
                        AnnotationSpec.builder(ClassName("kotlinx.serialization", "SerialName"))
                            .addMember("%S", propName)
                            .build()
                    )
                }
                
                classBuilder.addProperty(propBuilder.addModifiers(KModifier.ABSTRACT).build())
            }
        }

        parentMap[className]?.forEach { parentName ->
            classBuilder.addSuperinterface(ClassName(Main.TYPES_PKG, StringUtils.pascalCase(parentName)))
        }

        modelsFile.addType(classBuilder.build())
    }

    private fun generateDataClass(
        className: String,
        schema: JsonObject,
        properties: JsonObject,
        parentMap: MutableMap<String, MutableList<String>>
    ): TypeSpec {
        val classBuilder = TypeSpec.classBuilder(className)
            .addModifiers(KModifier.DATA)
            .addAnnotation(AnnotationSpec.builder(ClassName("kotlinx.serialization", "Serializable")).build())

        val constructor = FunSpec.constructorBuilder()
        val requiredSet = schema["required"]?.jsonArray?.map { it.jsonPrimitive.content }?.toSet() ?: emptySet()

        properties.forEach { (propName, propSchema) ->
            val propKotlinName = StringUtils.camelCase(propName)
            val propType = mapType(propSchema, Main.TYPES_PKG)
            val isRequired = requiredSet.contains(propName)
            val enum = propSchema.jsonObject["enum"] as? JsonArray
            
            if (enum == null) {
                constructor.addParameter(
                    ParameterSpec.builder(propKotlinName, if (isRequired) propType else propType.copy(nullable = true))
                        .build()
                )
                
                val propBuilder = PropertySpec.builder(propKotlinName, if (isRequired) propType else propType.copy(nullable = true))
                    .initializer(propKotlinName)
                
                if (propName != propKotlinName) {
                    propBuilder.addAnnotation(
                        AnnotationSpec.builder(ClassName("kotlinx.serialization", "SerialName"))
                            .addMember("%S", propName)
                            .build()
                    )
                }
            
                classBuilder.addProperty(propBuilder.build())
            } else {
                val names = enum.jsonArray.map { it.jsonPrimitive.content }
                classBuilder.addProperty(
                    PropertySpec.builder(propName, propType)
                        .apply { 
                            if (!isRequired) addAnnotation(Nullable::class) 

                            if (propName != propKotlinName) {
                                addAnnotation(
                                    AnnotationSpec.builder(ClassName("kotlinx.serialization", "SerialName"))
                                        .addMember("%S", propName)
                                        .build()
                                )
                            }
                        }
                        .initializer("%S",names.first())
                        .build()
                )
            }
        }

        parentMap[className]?.forEach { parentName ->
            classBuilder.addSuperinterface(ClassName(Main.TYPES_PKG, StringUtils.pascalCase(parentName)))
        }

        classBuilder.primaryConstructor(constructor.build())
        return classBuilder.build()
    }

    private fun generateEnumClass(name: String, schema: JsonObject): TypeSpec {
        val builder = TypeSpec.enumBuilder(name)
            .addAnnotation(AnnotationSpec.builder(ClassName("kotlinx.serialization", "Serializable")).build())

        (schema["enum"] as? JsonArray)?.forEach { value ->
            val rawValue = (value as JsonPrimitive).content
            val enumName = rawValue
                .replace(Regex("[^A-Za-z0-9]"), "_")
                .uppercase()
                .let { if (it.first().isDigit()) "_$it" else it }

            builder.addEnumConstant(enumName, TypeSpec.anonymousClassBuilder()
                .addAnnotation(AnnotationSpec.builder(ClassName("kotlinx.serialization", "SerialName"))
                    .addMember("%S", rawValue)
                    .build())
                .build())
        }

        return builder.build()
    }

    private fun mapType(
        schemaEl: JsonElement,
        typesPkg: String
    ): TypeName {
        if (schemaEl is JsonObject) {
            schemaEl["\$ref"]?.jsonPrimitive?.contentOrNull?.let { ref ->
                val refName = StringUtils.pascalCase(ref.substringAfterLast("/"))
                return ClassName(typesPkg, refName)
            }

            return when (schemaEl["type"]?.jsonPrimitive?.content) {
                "string" -> when (schemaEl["format"]?.jsonPrimitive?.content) {
                    "bigint", "u128", "int128", "uint128" -> ClassName("java.math", "BigInteger")
                    else -> STRING
                }
                "integer" -> LONG
                "number" -> DOUBLE
                "boolean" -> BOOLEAN
                "array" -> {
                    val itemsType = schemaEl["items"]?.let { 
                        mapType(it, typesPkg) 
                    } ?: STRING
                    LIST.parameterizedBy(itemsType)
                }
                "object" -> ClassName("kotlinx.serialization.json", "JsonObject")
                else -> STRING
            }
        }
        return STRING
    }

    private fun mergeAllOf(allOf: JsonArray, components: JsonObject): MutableMap<String, JsonElement> {
        val result = mutableMapOf<String, JsonElement>()
        for (part in allOf) {
            val partObj = part.jsonObject
            val ref = partObj["\$ref"]?.jsonPrimitive?.contentOrNull
            val resolved = if (ref != null) resolveRef(ref, components) ?: partObj else partObj
            if (resolved["allOf"] != null) {
                val nested = mergeAllOf(resolved["allOf"]!!.jsonArray, components)
                result.putAll(nested)
            }
            val props = resolved["properties"]?.jsonObject
            if (props != null) {
                for ((k, v) in props) result[k] = v
            } else if (resolved["type"] != null) {
                // 当 allOf 指向非 object 类型时，返回 type 自身
                result[""] = resolved
            }
        }
        return result
    }

    private fun resolveRef(ref: String, components: JsonObject): JsonObject? {
        val path = ref.removePrefix("#/").split("/")
        var node: JsonElement = components
        for (p in path) {
            node = if (node is JsonObject) node[p] ?: return null else return null
        }
        return node as? JsonObject
    }

    private fun mergeProperties(schema: JsonObject, components: JsonObject): JsonObject {
        return if (schema["allOf"] != null) {
            JsonObject(mergeAllOf(schema["allOf"]!!.jsonArray, components))
        } else {
            schema["properties"]?.jsonObject ?: JsonObject(emptyMap())
        }
    }

    fun generateAllOfType(
        name: String,
        schema: JsonObject,
        parentMap: MutableMap<String, MutableList<String>>,
        components: JsonObject
    ): TypeSpec {
        val allOf = schema["allOf"]?.jsonArray ?: return TypeSpec.classBuilder(StringUtils.pascalCase(name)).build()

        // 解析 allOf 中的第一个 $ref 或 type
        val baseSchemas = allOf.map { part ->
            val partObj = part.jsonObject
            val ref = partObj["\$ref"]?.jsonPrimitive?.contentOrNull
            if (ref != null) {
                resolveRef(ref, components) ?: partObj
            } else {
                partObj
            }
        }
        println("components: $components")

        // 假设 allOf 中只有一个 primitive (常见 alias 场景)
        val baseTypeSchema = baseSchemas.firstOrNull()
            ?: error("allOf for $name has no usable schema")

        val type = baseTypeSchema["type"]?.jsonPrimitive?.contentOrNull
        println("type: $type")

        return when (type) {
            "string" -> generateValueClass(name, STRING)
            "integer" -> generateValueClass(name, LONG)
            "boolean" -> generateValueClass(name, BOOLEAN)
            else -> {
                // 如果不是 primitive，就合并属性生成 data class
                val mergedProps = mergeProperties(schema, components)
                generateDataClass(StringUtils.pascalCase(name), schema, mergedProps, parentMap)
            }
        }
    }

    fun generateValueClass(name: String, kotlinType: TypeName): TypeSpec {
        return TypeSpec.classBuilder(name)
            .addAnnotation(ClassName("kotlinx.serialization", "Serializable"))
            .addAnnotation(ClassName("kotlin.jvm", "JvmInline"))
            .addModifiers(KModifier.VALUE)
            .primaryConstructor(
                FunSpec.constructorBuilder()
                    .addParameter("value", kotlinType)
                    .build()
            )
            .addProperty(
                PropertySpec.builder("value", kotlinType)
                    .initializer("value")
                    .build()
            )
            .build()
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