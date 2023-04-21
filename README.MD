# Kronos Multiplatform Library

![Maven Central](https://img.shields.io/maven-central/v/io.github.softartdev/kronos)
[![Build & Publish CI/CD](https://github.com/softartdev/Kronos-Multiplatform/actions/workflows/build_publish.yml/badge.svg)](https://github.com/softartdev/Kronos-Multiplatform/actions/workflows/build_publish.yml)

Kotlin Multiplatform library for network time synchronization. 
Extension for [kotlinx-datetime](https://github.com/Kotlin/kotlinx-datetime) library.
Supported platforms:
- Android
- iOS
- Desktop JVM (MacOS, Linux, Windows)
## Usage
### [kotlinx-datetime](https://github.com/Kotlin/kotlinx-datetime) extension
Use `Clock.Network.now()` to get the current network time. 
This can be used in conjunction with the built-in `Clock.System.now()` method.
```kotlin
val networkTime: Instant = Clock.Network.now()
val systemTime: Instant = Clock.System.now()
val diff: Duration = networkTime - systemTime
```
### Synchronization
When running the application, it is necessary to synchronize the time with the network using the platform code function:
- Android:
```kotlin
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Clock.Network.sync(applicationContext)
    }
}
```
- iOS:
```swift
@main
struct iosApp: App {
    init() {
        Clock.Network.sync()
    }
    var body: some Scene { ... }
}
```
- Desktop JVM:
```kotlin
fun main() {
    Clock.Network.sync()
    ...
}
```
### Installation
The latest release is available on [Maven Central](https://repo1.maven.org/maven2/io/github/softartdev/kronos/).
1. Add the Maven Central repository if it is not already there:
```kotlin
repositories {
    mavenCentral()
}
```
2. In multiplatform projects, add a dependency to the commonMain source set dependencies
```kotlin
commonMain {
    dependencies {
        implementation("io.github.softartdev:kronos:$latestVersion")
    }
}
```
## Implementation
Main common interface implemented with using:
- [lyft/Kronos](https://github.com/lyft/Kronos-Android) for Java & Android
- [MobileNativeFoundation/Kronos](https://github.com/MobileNativeFoundation/Kronos) for iOS

The project is assembled and checked thanks to:
- [Swift Klib Gradle Plugin](https://github.com/ttypic/swift-klib-plugin) including Swift source files in KMM shared module
- [Compose Multiplatform, by JetBrains](https://github.com/JetBrains/compose-jb) for UI samples
