package com.github.adriantodt.unifiedplatform.js

sealed class JsEnvironment {
    data class Browser(
        val isWebWorker: Boolean,
        val browser: BrowserInfo,
        val engine: EngineInfo,
        val platform: PlatformInfo,
        val osInfo: OsInfo
    ) : JsEnvironment() {
        override fun toString(): String {
            return "${if (isWebWorker) "browser-webworker" else "browser"}, $browser, $engine, $platform, $osInfo"
        }
    }

    data class Node(
        val model: String,
        val arch: String,
        val platform: String,
        val type: String,
        val version: String
    ) : JsEnvironment() {
        override fun toString(): String {
            return "node-$version, $platform${arch.uppercase()}"
        }
    }

    object Unknown : JsEnvironment() {
        override fun toString(): String {
            return "unknown"
        }
    }
}
