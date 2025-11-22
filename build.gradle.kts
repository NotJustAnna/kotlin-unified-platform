@file:OptIn(ExperimentalWasmDsl::class)

import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    kotlin("multiplatform") version "2.2.20"
    `maven-publish`
    id("org.jetbrains.dokka") version "2.1.0"
}

group = "net.notjustanna"
version = "2.0"

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(17)

    jvm {
        testRuns["test"].executionTask.configure {
            useJUnit()
        }
    }
    js(IR) {
        browser()
        nodejs()
    }

    // WebAssembly targets
    wasmJs {
        browser()
        nodejs()
    }
    wasmWasi {
        nodejs()
    }

    // Linux targets
    linuxX64()
    linuxArm64()

    // macOS targets
    macosX64()
    macosArm64()

    // Windows targets
    mingwX64()

    // iOS targets
    iosArm64()
    iosX64()
    iosSimulatorArm64()

    // watchOS targets
    watchosArm32()
    watchosArm64()
    watchosX64()
    watchosSimulatorArm64()
    watchosDeviceArm64()

    // tvOS targets
    tvosArm64()
    tvosX64()
    tvosSimulatorArm64()

    // Apply the default native source-set hierarchy instead of configuring it manually
    applyDefaultHierarchyTemplate()

    sourceSets {
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation("org.apache.commons:commons-lang3:3.12.0")
            }
        }
        val jsMain by getting {
            dependencies {
                implementation(npm("bowser", "2.11.0"))
            }
        }
    }
}


tasks {
    val dokkaGeneratePublicationHtml by getting
    register<Jar>("dokkaJar") {
        from(dokkaGeneratePublicationHtml)
        dependsOn(dokkaGeneratePublicationHtml)
        archiveClassifier.set("javadoc")
    }
}

publishing {
    publications.withType<MavenPublication> {
        artifact(tasks["dokkaJar"])
    }
    repositories {
        maven {
            name = "local"
            url = uri("${project.rootDir}/.repo")
        }
    }
}
