plugins {
    kotlin("jvm")
    application
    kotlin("plugin.serialization")
    id("org.openapi.generator")
}
repositories { mavenCentral() }
dependencies {
    implementation("com.squareup:kotlinpoet:1.14.2")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
    implementation("com.squareup.okhttp3:okhttp:4.11.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.openapitools:openapi-generator:7.15.0")

    testImplementation(kotlin("test"))
    testImplementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
}
application { mainClass.set("io.near.codegen.Main") }

// java -jar openapi-generator-cli.jar meta -o out/generators/my-codegen -n my-codegen -p com.my.company.codegen