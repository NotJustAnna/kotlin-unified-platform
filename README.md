# Kotlin Unified Platform

[![Build](https://github.com/NotJustAnna/kotlin-unified-platform/actions/workflows/build.yml/badge.svg)](https://github.com/NotJustAnna/kotlin-unified-platform/actions/workflows/build.yml)
[![Maven](https://img.shields.io/badge/maven-repository-blue)](https://github.com/NotJustAnna/kotlin-unified-platform/tree/maven)

A comprehensive Kotlin Multiplatform library for runtime platform detection across all major platforms and architectures.

## Features

- **Universal Platform Detection**: Detect whether your code is running on JVM, JavaScript, WebAssembly, or Native platforms
- **Detailed Platform Information**: Get OS family, CPU architecture, Java version, browser details, and more
- **Extensive Platform Coverage**: Support for 20+ targets including:
  - **JVM**: All platforms with Java 17+
  - **JavaScript**: Browser and Node.js environments
  - **WebAssembly**: wasmJs and wasmWasi targets
  - **Native**: Linux, macOS, Windows, iOS, watchOS, tvOS
- **Zero Dependencies**: Lightweight library with no external dependencies (except platform-specific npm packages for JS)
- **Type-Safe**: Leverages Kotlin's sealed classes for exhaustive when expressions

## Supported Platforms

### Desktop & Server
- **JVM**: Cross-platform (requires Java 17+)
- **Native Linux**: x64, ARM64
- **Native macOS**: x64 (Intel), ARM64 (Apple Silicon)
- **Native Windows**: x64

### Web
- **JavaScript**: Browser and Node.js
- **WebAssembly**: JavaScript (wasmJs) and WASI (wasmWasi)

### Mobile & Embedded
- **iOS**: ARM64 (devices), x64 (simulator), ARM64 (simulator)
- **watchOS**: ARM32, ARM64, x64 (simulator), ARM64 (simulator), ARM64 (device)
- **tvOS**: ARM64 (devices), x64 (simulator), ARM64 (simulator)

## Installation

### Gradle (Kotlin DSL)

```kotlin
repositories {
    maven("https://raw.githubusercontent.com/NotJustAnna/kotlin-unified-platform/maven/")
}

dependencies {
    implementation("net.notjustanna:kotlin-unified-platform:1.3")
}
```

### Gradle (Groovy DSL)

```groovy
repositories {
    maven {
        url 'https://raw.githubusercontent.com/NotJustAnna/kotlin-unified-platform/maven/'
    }
}

dependencies {
    implementation 'net.notjustanna:kotlin-unified-platform:1.3'
}
```

## Usage

### Basic Platform Detection

```kotlin
import net.notjustanna.unifiedplatform.currentPlatform
import net.notjustanna.unifiedplatform.UnifiedPlatform

fun main() {
    when (val platform = currentPlatform) {
        is UnifiedPlatform.Java -> {
            println("Running on JVM")
            println("OS: ${platform.osFamily}")
            println("Java Version: ${platform.version}")
        }
        is UnifiedPlatform.JavaScript -> {
            println("Running on JavaScript")
            println("Environment: ${platform.environment}")
        }
        is UnifiedPlatform.WebAssembly -> {
            println("Running on WebAssembly")
            println("Target: ${platform.target}")
        }
        is UnifiedPlatform.Native -> {
            println("Running on Native")
            println("OS: ${platform.osFamily}")
            println("Architecture: ${platform.cpuArchitecture}")
        }
    }
}
```

### Detailed JavaScript Environment Detection

```kotlin
import net.notjustanna.unifiedplatform.currentPlatform
import net.notjustanna.unifiedplatform.UnifiedPlatform
import net.notjustanna.unifiedplatform.js.JsEnvironment

fun detectJsEnvironment() {
    val platform = currentPlatform as? UnifiedPlatform.JavaScript ?: return

    when (val env = platform.environment) {
        is JsEnvironment.Browser -> {
            println("Browser: ${env.browserInfo.name} ${env.browserInfo.version}")
            println("Engine: ${env.engineInfo.name} ${env.engineInfo.version}")
            println("Platform: ${env.platformInfo.type}")
            println("OS: ${env.osInfo.name} ${env.osInfo.version}")
            println("Is Web Worker: ${env.isWebWorker}")
        }
        is JsEnvironment.Node -> {
            println("Node.js: ${env.version}")
            println("CPU: ${env.cpuModel}")
            println("Architecture: ${env.arch}")
            println("Platform: ${env.platform}")
        }
        is JsEnvironment.Unknown -> {
            println("Unknown JavaScript environment")
        }
    }
}
```

### OS Family Detection

```kotlin
import net.notjustanna.unifiedplatform.OsFamily

fun checkOsFamily(osFamily: OsFamily) {
    when (osFamily) {
        OsFamily.LINUX -> println("Linux detected")
        OsFamily.WINDOWS -> println("Windows detected")
        OsFamily.MACOSX -> println("macOS detected")
        OsFamily.IOS -> println("iOS detected")
        OsFamily.TVOS -> println("tvOS detected")
        OsFamily.WATCHOS -> println("watchOS detected")
        OsFamily.ANDROID -> println("Android detected")
        OsFamily.WASM -> println("WebAssembly detected")
        OsFamily.UNKNOWN -> println("Unknown OS")
    }
}
```

### CPU Architecture Detection

```kotlin
import net.notjustanna.unifiedplatform.CpuArchitecture

fun checkArchitecture(arch: CpuArchitecture) {
    when (arch) {
        CpuArchitecture.X64 -> println("x86-64 (AMD64)")
        CpuArchitecture.ARM64 -> println("ARM 64-bit")
        CpuArchitecture.X86 -> println("x86 32-bit")
        CpuArchitecture.ARM32 -> println("ARM 32-bit")
        CpuArchitecture.MIPS32 -> println("MIPS 32-bit")
        CpuArchitecture.MIPSEL32 -> println("MIPS 32-bit Little Endian")
        CpuArchitecture.WASM32 -> println("WebAssembly 32-bit")
        CpuArchitecture.UNKNOWN -> println("Unknown architecture")
    }
}
```

## How It Works

This library uses Kotlin Multiplatform's `expect`/`actual` mechanism to provide platform-specific implementations:

- **JVM**: Uses `System.getProperty()` to detect OS and Java version
- **JavaScript**: Uses the [Bowser](https://github.com/bowser-dev/bowser) library for browser detection and Node.js APIs for server-side detection
- **WebAssembly**: Provides static platform identification for wasmJs and wasmWasi targets
- **Native**: Uses Kotlin/Native's `Platform` API to detect OS and CPU architecture

## API Overview

### UnifiedPlatform (Sealed Class)

```kotlin
sealed class UnifiedPlatform {
    data class Native(val osFamily: OsFamily, val cpuArchitecture: CpuArchitecture)
    data class Java(val osFamily: OsFamily, val version: JavaVersion)
    data class JavaScript(val osFamily: OsFamily, val environment: JsEnvironment)
    data class WebAssembly(val target: WasmTarget)
}
```

### Enums

- `OsFamily`: LINUX, WINDOWS, MACOSX, IOS, TVOS, WATCHOS, ANDROID, WASM, UNKNOWN
- `CpuArchitecture`: X86, X64, ARM32, ARM64, MIPS32, MIPSEL32, WASM32, UNKNOWN
- `WasmTarget`: JS, WASI
- `JavaVersion`: JAVA_8, JAVA_9, ..., JAVA_24, FUTURE

### JavaScript-Specific Classes

- `JsEnvironment.Browser`: Contains browser, engine, platform, and OS information
- `JsEnvironment.Node`: Contains Node.js version and system information
- `BrowserInfo`: Browser name and version
- `EngineInfo`: JavaScript engine name and version
- `PlatformInfo`: Platform type, vendor, and model
- `OsInfo`: Operating system name, version, and version name

## Building from Source

```bash
# Clone the repository
git clone https://github.com/NotJustAnna/kotlin-unified-platform.git
cd kotlin-unified-platform

# Build all targets
./gradlew build

# Publish to local Maven repository
./gradlew publishToMavenLocal
```

## Requirements

- **Kotlin**: 2.2.20+
- **Gradle**: 8.11.1+
- **JDK**: 17+ (for JVM target and build process)

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## Changelog

### Version 1.3 (Latest)
- Updated to Kotlin 2.2.20
- Added WebAssembly support (wasmJs, wasmWasi)
- Added Apple platform support (iOS, watchOS, tvOS)
- Migrated to new package namespace: `net.notjustanna`
- Updated to Gradle 8.11.1
- Improved type safety with sealed classes

### Version 1.2
- Added JavaScript browser and Node.js detection
- Improved Native platform detection

### Version 1.0
- Initial release with JVM and Native support
