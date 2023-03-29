import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    kotlin("native.cocoapods")
    id("com.android.application")
}

kotlin {
    android()

    jvm("desktop")

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        version = "1.0.0"
        summary = "Compose application framework"
        homepage = "empty"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":kronos"))
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation("io.github.aakira:napier:2.6.1")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.0-Beta")
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        val androidMain by getting {
            dependencies {
                implementation("androidx.appcompat:appcompat:1.6.1")
                implementation("androidx.activity:activity-compose:1.7.0")
                implementation("androidx.compose.ui:ui-tooling:1.4.0")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.0-Beta")
            }
        }

        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.common)
                implementation(compose.desktop.currentOs)
            }
        }

        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
            }
        }

        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    namespace = "com.softartdev.kronos.sample"
    compileSdk = 33

    defaultConfig {
        minSdk = 21
        targetSdk = 33

        applicationId = "com.softartdev.kronos.sample"
        versionCode = 1
        versionName = "1.0.0"
    }
    sourceSets["main"].apply {
        manifest.srcFile("src/androidMain/AndroidManifest.xml")
        res.srcDirs("src/androidMain/resources")
    }
    kotlin {
        jvmToolchain(11)
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    packagingOptions {
        resources.excludes.add("META-INF/**")
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.softartdev.kronos.sample"
            packageVersion = "1.0.0"
        }
    }
}
