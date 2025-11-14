plugins {
    kotlin("multiplatform") version "2.2.20"
    `maven-publish`
    id("org.jetbrains.dokka") version "2.1.0"
}

group = "net.notjustanna"
version = "1.3"

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

    sourceSets {
        val commonMain by getting
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
        val jvmTest by getting
        val jsMain by getting {
            dependencies {
                implementation(npm("bowser", "2.11.0"))
            }
        }

        // WebAssembly source sets
        val wasmJsMain by getting {
            dependsOn(commonMain)
        }
        val wasmWasiMain by getting {
            dependsOn(commonMain)
        }

        // Native source sets hierarchy
        val nativeMain by creating {
            dependsOn(commonMain)
        }
        val nativeTest by creating {
            dependsOn(nativeMain)
        }

        // Linux targets
        val linuxX64Main by getting {
            dependsOn(nativeMain)
        }
        val linuxX64Test by getting {
            dependsOn(nativeTest)
        }
        val linuxArm64Main by getting {
            dependsOn(nativeMain)
        }
        val linuxArm64Test by getting {
            dependsOn(nativeTest)
        }

        // macOS targets
        val macosX64Main by getting {
            dependsOn(nativeMain)
        }
        val macosX64Test by getting {
            dependsOn(nativeTest)
        }
        val macosArm64Main by getting {
            dependsOn(nativeMain)
        }
        val macosArm64Test by getting {
            dependsOn(nativeTest)
        }

        // Windows targets
        val mingwX64Main by getting {
            dependsOn(nativeMain)
        }
        val mingwX64Test by getting {
            dependsOn(nativeTest)
        }

        // iOS targets
        val iosArm64Main by getting {
            dependsOn(nativeMain)
        }
        val iosArm64Test by getting {
            dependsOn(nativeTest)
        }
        val iosX64Main by getting {
            dependsOn(nativeMain)
        }
        val iosX64Test by getting {
            dependsOn(nativeTest)
        }
        val iosSimulatorArm64Main by getting {
            dependsOn(nativeMain)
        }
        val iosSimulatorArm64Test by getting {
            dependsOn(nativeTest)
        }

        // watchOS targets
        val watchosArm32Main by getting {
            dependsOn(nativeMain)
        }
        val watchosArm32Test by getting {
            dependsOn(nativeTest)
        }
        val watchosArm64Main by getting {
            dependsOn(nativeMain)
        }
        val watchosArm64Test by getting {
            dependsOn(nativeTest)
        }
        val watchosX64Main by getting {
            dependsOn(nativeMain)
        }
        val watchosX64Test by getting {
            dependsOn(nativeTest)
        }
        val watchosSimulatorArm64Main by getting {
            dependsOn(nativeMain)
        }
        val watchosSimulatorArm64Test by getting {
            dependsOn(nativeTest)
        }
        val watchosDeviceArm64Main by getting {
            dependsOn(nativeMain)
        }
        val watchosDeviceArm64Test by getting {
            dependsOn(nativeTest)
        }

        // tvOS targets
        val tvosArm64Main by getting {
            dependsOn(nativeMain)
        }
        val tvosArm64Test by getting {
            dependsOn(nativeTest)
        }
        val tvosX64Main by getting {
            dependsOn(nativeMain)
        }
        val tvosX64Test by getting {
            dependsOn(nativeTest)
        }
        val tvosSimulatorArm64Main by getting {
            dependsOn(nativeMain)
        }
        val tvosSimulatorArm64Test by getting {
            dependsOn(nativeTest)
        }
    }
}


tasks {
    register<Jar>("dokkaJar") {
        from(dokkaHtml)
        dependsOn(dokkaHtml)
        archiveClassifier.set("javadoc")
    }
}

publishing {
    publications.withType<MavenPublication> {
        artifact(tasks["dokkaJar"])
    }
    // select the repositories you want to publish to
    repositories {
        maven {
            url = uri("https://maven.cafeteria.dev/releases")

            credentials {
                username = "${project.findProperty("mcdUsername") ?: System.getenv("MCD_USERNAME")}"
                password = "${project.findProperty("mcdPassword") ?: System.getenv("MCD_PASSWORD")}"
            }
            authentication {
                create("basic", BasicAuthentication::class.java)
            }
        }
        mavenLocal()
    }
}
