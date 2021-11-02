package com.github.adriantodt.unifiedplatform

import com.github.adriantodt.unifiedplatform.js.JsEnvironment

sealed class UnifiedPlatform {
    data class Native(val osFamily: OsFamily, val cpuArchitecture: CpuArchitecture): UnifiedPlatform() {
        override fun toString(): String {
            return "[native, ${osFamily.name.lowercase()}${cpuArchitecture.name}]"
        }
    }

    data class Java(val osFamily: OsFamily, val version: JavaVersion): UnifiedPlatform() {
        override fun toString(): String {
            return "[jvm-v${version.versionName}, ${osFamily.name.lowercase()}]"
        }
    }

    data class JavaScript(val osFamily: OsFamily, val environment: JsEnvironment) : UnifiedPlatform() {
        override fun toString(): String {
            return "[js, $environment]"
        }
    }
}
