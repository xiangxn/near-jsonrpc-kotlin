plugins { kotlin("jvm") }
dependencies {
    testImplementation(kotlin("test"))
    implementation(project(":near-jsonrpc-client"))
    implementation(project(":near-jsonrpc-types"))
}
