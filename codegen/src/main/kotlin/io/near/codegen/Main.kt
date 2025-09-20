package io.near.codegen

import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import kotlinx.serialization.json.*
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.math.BigInteger
import java.util.*
import com.squareup.kotlinpoet.CodeBlock
// import com.facebook.ktfmt.format

fun pascalCase(s: String): String {
    val parts = s.split(Regex("[^A-Za-z0-9]+")).filter { it.isNotBlank() }
    return parts.joinToString("") { it.replaceFirstChar { c -> c.uppercase(Locale.getDefault()) } }
}

fun camelCase(s: String): String {
    val parts = s.split(Regex("[^A-Za-z0-9]+")).filter { it.isNotBlank() }
    return parts.mapIndexed { i, p -> if (i == 0) p.lowercase(Locale.getDefault()) else p.replaceFirstChar { c -> c.uppercase(Locale.getDefault()) } }.joinToString("")
}

fun fetchSpec(urlOrPath: String): JsonObject {
    return if (urlOrPath.startsWith("http://") || urlOrPath.startsWith("https://")) {
        val client = OkHttpClient()
        val req = Request.Builder().url(urlOrPath).build()
        client.newCall(req).execute().use { resp ->
            val body = resp.body?.string() ?: throw RuntimeException("Empty response from spec URL")
            Json.parseToJsonElement(body).jsonObject
        }
    } else {
        val text = File(urlOrPath).readText()
        Json.parseToJsonElement(text).jsonObject
    }
}

fun resolveRef(ref: String, components: JsonObject): JsonObject? {
    val path = ref.removePrefix("#/").split("/")
    var node: JsonElement = components
    for (p in path) {
        node = if (node is JsonObject) node[p] ?: return null else return null
    }
    return node as? JsonObject
}

fun mergeAllOf(allOf: JsonArray, components: JsonObject): MutableMap<String, JsonElement> {
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
        }
    }
    return result
}

fun mapType(schemaEl: JsonElement, typesPkg: String): TypeName {
    if (schemaEl is JsonObject) {
        val ref = schemaEl["\$ref"]?.jsonPrimitive?.contentOrNull
        if (ref != null) return ClassName(typesPkg, pascalCase(ref.substringAfterLast("/")))
        val type = schemaEl["type"]?.jsonPrimitive?.contentOrNull
        val format = schemaEl["format"]?.jsonPrimitive?.contentOrNull
        when (type) {
            "string" -> {
                if (format != null && (format.contains("int") || format.contains("uint") || format.contains("u128") || format.contains("int128"))) {
                    return ClassName("java.math", "BigInteger")
                }
                return STRING
            }
            "integer" -> {
                if (format != null && (format.contains("int") || format.contains("uint") || format.contains("u128") || format.contains("int128"))) {
                    return ClassName("java.math", "BigInteger")
                }
                return LONG
            }
            "number" -> return DOUBLE
            "boolean" -> return BOOLEAN
            "array" -> {
                val items = schemaEl["items"]
                val itemType = if (items != null) mapType(items, typesPkg) else STRING
                return LIST.parameterizedBy(itemType)
            }
            "object" -> return ClassName("kotlinx.serialization.json", "JsonObject")
        }
    }
    return STRING
}

fun mockValue(schema: JsonObject): CodeBlock {
    val type = (schema["type"] as? JsonPrimitive)?.contentOrNull
    val exampleEl = schema["example"]
    val defaultEl = schema["default"]
    val enumVals = schema["enum"] as? JsonArray
    val format = (schema["format"] as? JsonPrimitive)?.contentOrNull

    if (exampleEl != null) {
        return when (exampleEl) {
            is JsonPrimitive -> CodeBlock.of("%S", exampleEl.content)
            is JsonObject, is JsonArray -> CodeBlock.of("null /* complex example */")
            else -> CodeBlock.of("null")
        }
    }

    if (defaultEl != null) {
        return when (defaultEl) {
            is JsonPrimitive -> CodeBlock.of("%S", defaultEl.content)
            is JsonObject, is JsonArray -> CodeBlock.of("null /* complex default */")
            else -> CodeBlock.of("null")
        }
    }

    if (enumVals != null && enumVals.isNotEmpty()) {
        val first = enumVals.first()
        return if (first is JsonPrimitive) CodeBlock.of("%S", first.content) else CodeBlock.of("null")
    }

    return when {
        type == "string" -> when (format) {
            "date-time" -> CodeBlock.of("%S", "2025-01-01T00:00:00Z")
            "uuid" -> CodeBlock.of("%S", "00000000-0000-0000-0000-000000000000")
            else -> CodeBlock.of("%S", "")
        }
        type == "integer" || type == "number" -> CodeBlock.of("0")
        type == "boolean" -> CodeBlock.of("false")
        type == "array" -> CodeBlock.of("emptyList()")
        type == "object" -> CodeBlock.of("null /* TODO: nested mock */")
        else -> CodeBlock.of("null")
    }
}

fun generateMockConstructorArgs(schema: JsonObject): List<CodeBlock> {
    val props = schema["properties"] as? JsonObject ?: return emptyList()
    return props.entries.map { (name, propSchemaElement) ->
        val propSchema = propSchemaElement.jsonObject
        CodeBlock.of("%L = %L", name, mockValue(propSchema))
    }
}

fun formatWithSpotless() {
    try {
        println("Formatting generated code with Spotless (Google style)...")
        val pb = ProcessBuilder(
            "gradle",
            "spotlessApply",
            "--quiet"
        ).directory(File("../"))
        pb.inheritIO()
        val process = pb.start()
        val exitCode = process.waitFor()
        if (exitCode != 0) {
            println("Warning: Spotless formatting failed (exit code $exitCode)")
        }
        println("✅ Formatting complete.")
    } catch (e: Exception) {
        println("Warning: Could not format with Spotless: ${e.message}")
    }
}


fun main(args: Array<String>) {
    val specUrl = if (args.isNotEmpty()) args[0] else "https://raw.githubusercontent.com/near/nearcore/master/chain/jsonrpc/openapi/openapi.json"
    println("Fetching OpenAPI spec from: $specUrl")
    val root = fetchSpec(specUrl)
    val components = root["components"]?.jsonObject ?: JsonObject(emptyMap())
    val schemas = components["schemas"]?.jsonObject ?: JsonObject(emptyMap())

    val typesPkg = "io.near.jsonrpc.types"
    val clientPkg = "io.near.jsonrpc.client"

    // Build parent map for oneOf/discriminator -> child relationships
    val parentMap = mutableMapOf<String, MutableList<String>>()
    for ((name, el) in schemas) {
        val obj = el.jsonObject
        val oneOf = obj["oneOf"]?.jsonArray
        if (oneOf != null) {
            for (item in oneOf) {
                val ref = item.jsonObject["\$ref"]?.jsonPrimitive?.contentOrNull
                if (ref != null) {
                    val child = ref.substringAfterLast("/")
                    parentMap.computeIfAbsent(child) { mutableListOf() }.add(name)
                }
            }
        }
        val discr = obj["discriminator"]?.jsonObject
        if (discr != null) {
            val mapping = discr["mapping"]?.jsonObject
            if (mapping != null) {
                for ((_, v) in mapping) {
                    val ref = v.jsonPrimitive.contentOrNull ?: continue
                    val child = ref.substringAfterLast("/")
                    parentMap.computeIfAbsent(child) { mutableListOf() }.add(name)
                }
            }
        }
    }

    val modelsFile = FileSpec.builder(typesPkg, "NearModels")
    for ((name, el) in schemas) {
        try {
            val className = pascalCase(name)
            val obj = el.jsonObject
            val hasOneOf = obj["oneOf"] != null
            val hasDiscriminator = obj["discriminator"] != null
            if (hasOneOf || hasDiscriminator) {
                // sealed interface as parent
                val interfaceSpec = TypeSpec.interfaceBuilder(className)
                    .addModifiers(KModifier.SEALED)
                    .addAnnotation(AnnotationSpec.builder(ClassName("kotlinx.serialization", "Serializable")).build())
                    .build()
                modelsFile.addType(interfaceSpec)
            }

            // Collect properties, merging allOf if present
            val ownProps = obj["properties"]?.jsonObject ?: JsonObject(emptyMap())
            val mergedProps = if (obj["allOf"] != null) {
                val merged = mergeAllOf(obj["allOf"]!!.jsonArray, components)
                val combined = mutableMapOf<String, JsonElement>()
                combined.putAll(merged)
                for ((k, v) in ownProps) combined[k] = v
                JsonObject(combined)
            } else {
                ownProps
            }

            if (mergedProps.isNotEmpty()) {
                val requiredSet = obj["required"]?.jsonArray?.mapNotNull { it.jsonPrimitive.contentOrNull }?.toSet() ?: emptySet()
                val ctor = FunSpec.constructorBuilder()
                val classBuilder = TypeSpec.classBuilder(className).addModifiers(KModifier.DATA)
                val propSpecs = mutableListOf<PropertySpec>()
                for ((pname, pschema) in mergedProps) {
                    val kotlinName = camelCase(pname)
                    val tname = mapType(pschema, typesPkg)
                    val isRequired = requiredSet.contains(pname)
                    val paramType = if (isRequired) tname else tname.copy(nullable = true)
                    ctor.addParameter(kotlinName, paramType)
                    val propBuilder = PropertySpec.builder(kotlinName, paramType).initializer(kotlinName)
                    if (kotlinName != pname) {
                        propBuilder.addAnnotation(AnnotationSpec.builder(ClassName("kotlinx.serialization", "SerialName")).addMember("%S", pname).build())
                    }
                    if (tname is ClassName && tname.simpleName == "BigInteger") {
                        val serializerClass = ClassName("io.near.jsonrpc.types", "BigIntegerAsStringSerializer")
                        propBuilder.addAnnotation(AnnotationSpec.builder(ClassName("kotlinx.serialization", "Serializable")).addMember("with = %T::class", serializerClass).build())
                    }
                    propSpecs.add(propBuilder.build())
                }
                classBuilder.primaryConstructor(ctor.build())
                propSpecs.forEach { classBuilder.addProperty(it) }
                classBuilder.addAnnotation(AnnotationSpec.builder(ClassName("kotlinx.serialization", "Serializable")).build())

                // implement parents if present
                val parents = parentMap[name]
                if (parents != null) {
                    for (p in parents) classBuilder.addSuperinterface(ClassName(typesPkg, pascalCase(p)))
                }

                modelsFile.addType(classBuilder.build())

                // generate validate() extension (basic checks)
                val validateFun = FunSpec.builder("validate")
                    .receiver(ClassName(typesPkg, className))
                    .returns(ClassName("kotlin.collections", "List").parameterizedBy(ClassName("kotlin", "String")))
                    .addStatement("val errors = mutableListOf<String>()")
                for ((pname, pschema) in mergedProps) {
                    val kotlinName = camelCase(pname)
                    val schemaObj = pschema.jsonObject
                    val minimum = schemaObj["minimum"]?.jsonPrimitive?.contentOrNull
                    val maximum = schemaObj["maximum"]?.jsonPrimitive?.contentOrNull
                    val pattern = schemaObj["pattern"]?.jsonPrimitive?.contentOrNull
                    val minLength = schemaObj["minLength"]?.jsonPrimitive?.contentOrNull
                    val maxLength = schemaObj["maxLength"]?.jsonPrimitive?.contentOrNull
                    if (minimum != null) {
                        validateFun.beginControlFlow("if (this.%N != null)", kotlinName)
                        validateFun.addStatement("try { if ((this.%N as Number).toDouble() < %L) errors.add(%S) } catch(e: Exception) {}", kotlinName, minimum, "$pname must be >= $minimum")
                        validateFun.endControlFlow()
                    }
                    if (maximum != null) {
                        validateFun.beginControlFlow("if (this.%N != null)", kotlinName)
                        validateFun.addStatement("try { if ((this.%N as Number).toDouble() > %L) errors.add(%S) } catch(e: Exception) {}", kotlinName, maximum, "$pname must be <= $maximum")
                        validateFun.endControlFlow()
                    }
                    if (pattern != null) {
                        validateFun.beginControlFlow("if (this.%N != null)", kotlinName)
                        validateFun.addStatement("val _s = this.%N as? String", kotlinName)
                        validateFun.addStatement("if (_s != null && !Regex(%S).matches(_s)) errors.add(%S)", pattern, "$pname does not match pattern")
                        validateFun.endControlFlow()
                    }
                    if (minLength != null) {
                        validateFun.beginControlFlow("if (this.%N != null)", kotlinName)
                        validateFun.addStatement("val _s = this.%N as? String", kotlinName)
                        validateFun.addStatement("if (_s != null && _s.length < %L) errors.add(%S)", minLength, "$pname length must be >= $minLength")
                        validateFun.endControlFlow()
                    }
                    if (maxLength != null) {
                        validateFun.beginControlFlow("if (this.%N != null)", kotlinName)
                        validateFun.addStatement("val _s = this.%N as? String", kotlinName)
                        validateFun.addStatement("if (_s != null && _s.length > %L) errors.add(%S)", maxLength, "$pname length must be <= $maxLength")
                        validateFun.endControlFlow()
                    }
                }
                validateFun.addStatement("return errors")
                modelsFile.addFunction(validateFun.build())
            } else {
                // fallback typealias
                modelsFile.addTypeAlias(TypeAliasSpec.builder(className, STRING).build())
            }
        } catch (e: Exception) {
            System.err.println("Error generating model for $name: ${e.message}")
        }
    }

    // write models to types module
    val typesOut = File("../near-jsonrpc-types/src/main/kotlin")
    typesOut.mkdirs()
    modelsFile.build().writeTo(typesOut)

    // Generate unit tests
    val testDir = File("../near-jsonrpc-types/src/test/kotlin")
    testDir.mkdirs()
    for ((name, schemaElement) in schemas) {
        val schema = schemaElement.jsonObject
        val className = ClassName("io.near.jsonrpc.types", name)

        val constructorArgs = generateMockConstructorArgs(schema)

        val testFun = FunSpec.builder("test$name")
            .addAnnotation(ClassName("kotlin.test", "Test"))
            .addCode(
                """
                val original = %T(
                    ${constructorArgs.joinToString(",\n") { it.toString() }}
                )
                val json = kotlinx.serialization.json.Json.encodeToString(%T.serializer(), original)
                val decoded = kotlinx.serialization.json.Json.decodeFromString(%T.serializer(), json)
                kotlin.test.assertEquals(original, decoded)
                """.trimIndent(),
                className, className, className
            )
            .build()

        val testClass = TypeSpec.classBuilder("${name}Test")
            .addFunction(testFun)
            .build()

        val file = FileSpec.builder("io.near.jsonrpc.types", "${name}Test")
            .addType(testClass)
            .build()

        file.writeTo(testDir)
    }
    println("✅ The mock unit tests have been generated in the $testDir")

    // Generate client API class
    val apiFile = FileSpec.builder(clientPkg, "NearJsonRpcApi")
    val clientClass = TypeSpec.classBuilder("NearJsonRpcApi")
        .primaryConstructor(FunSpec.constructorBuilder().addParameter("rpcClient", ClassName("io.near.jsonrpc.client", "JsonRpcClient")).build())
        .addProperty(PropertySpec.builder("rpcClient", ClassName("io.near.jsonrpc.client", "JsonRpcClient")).initializer("rpcClient").build())

    val paths = root["paths"]?.jsonObject ?: JsonObject(emptyMap())
    for ((_, pathObj) in paths) {
        for ((_, methodVal) in pathObj.jsonObject) {
            val methodObj = methodVal.jsonObject
            val opId = methodObj["operationId"]?.jsonPrimitive?.contentOrNull ?: continue
            val funcName = camelCase(opId)
            // determine return type
            var returnType: TypeName = ClassName("kotlinx.serialization.json", "JsonElement")
            val responses = methodObj["responses"]?.jsonObject
            val resp200 = responses?.get("200")?.jsonObject
            val content = resp200?.get("content")?.jsonObject
            val appJson = content?.get("application/json")?.jsonObject
            val schema = appJson?.get("schema")
            if (schema != null) {
                val ref = schema.jsonObject["\$ref"]?.jsonPrimitive?.contentOrNull
                if (ref != null) {
                    returnType = ClassName(typesPkg, pascalCase(ref.substringAfterLast("/")))
                } else {
                    returnType = ClassName("kotlinx.serialization.json", "JsonElement")
                }
            }
            // parameter: if requestBody present with $ref -> concrete type; else JsonElement?
            var paramType: TypeName = ClassName("kotlinx.serialization.json", "JsonElement").copy(nullable = true)
            val requestBody = methodObj["requestBody"]?.jsonObject
            val rbSchema = requestBody?.get("content")?.jsonObject?.get("application/json")?.jsonObject?.get("schema")
            if (rbSchema != null) {
                val rbRef = rbSchema.jsonObject["\$ref"]?.jsonPrimitive?.contentOrNull
                if (rbRef != null) {
                    paramType = ClassName(typesPkg, pascalCase(rbRef.substringAfterLast("/"))).copy(nullable = true)
                }
            }

            val funSpec = FunSpec.builder(funcName)
                .addModifiers(KModifier.SUSPEND)
                .addParameter("params", paramType)
                .returns(returnType)
                .addStatement("val res = rpcClient.call<%T>(%S, params)", returnType, opId)
                .addStatement("return res")
            clientClass.addFunction(funSpec.build())
        }
    }

    apiFile.addType(clientClass.build())
    val clientOut = File("../near-jsonrpc-client/src/main/kotlin")
    clientOut.mkdirs()
    apiFile.build().writeTo(clientOut)

    formatWithSpotless()
    println("✅ Generation finished: models -> near-jsonrpc-types, api -> near-jsonrpc-client")
}
