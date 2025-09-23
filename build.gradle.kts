plugins {
    kotlin("jvm") version "1.9.23" apply false
    kotlin("plugin.serialization") version "1.9.23" apply false
    id("org.openapi.generator") version "7.4.0" apply false
    id("maven-publish")
    id("signing")
    id("com.diffplug.spotless") version "6.25.0"
}

allprojects {
    group = "io.near"
    version = "0.1.0"
    repositories { 
        mavenCentral() 
        gradlePluginPortal()
    }
}

spotless {
    kotlin {
        ktfmt().googleStyle()
        target(
            "near-jsonrpc-types/src/**/*.kt",
            "near-jsonrpc-client/src/**/*.kt"
        )
    }
}
