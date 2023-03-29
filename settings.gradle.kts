rootProject.name = "Kronos Multiplatform"
include(":kronos")
include(":sampleApp")

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
    plugins {
        val kotlin = "1.8.10"
        kotlin("android").version(kotlin)
        kotlin("multiplatform").version(kotlin)
        kotlin("native.cocoapods").version(kotlin)

        id("com.android.application").version("7.4.2")
        id("org.jetbrains.compose").version("1.4.0-alpha01-dev980")
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}
