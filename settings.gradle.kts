rootProject.name = "Kronos Multiplatform"
includeBuild("convention-plugins")
include(":kronos")
include(":sampleApp")

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}
