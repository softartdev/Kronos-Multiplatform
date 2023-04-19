plugins {
    `kotlin-dsl` // Is needed to turn our build logic written in Kotlin into the Gradle Plugin
}

repositories {
    gradlePluginPortal() // To use 'maven-publish' and 'signing' plugins in our own plugin
}

dependencies {
    implementation("io.codearte.gradle.nexus:gradle-nexus-staging-plugin:0.30.0")
}