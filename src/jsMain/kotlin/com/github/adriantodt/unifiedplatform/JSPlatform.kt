package com.github.adriantodt.unifiedplatform

import Bowser.OS_MAP
import Bowser.parse
import com.github.adriantodt.unifiedplatform.js.*
import global
import kotlinx.browser.window

actual val currentPlatform: UnifiedPlatform = detectPlatform()

private fun detectPlatform(): UnifiedPlatform.JavaScript {
    return when {
        isBrowser || isWebWorker -> detectBrowserEnvironment()
        isNode -> detectNodeEnviroment()
        else -> UnifiedPlatform.JavaScript(OsFamily.UNKNOWN, JsEnvironment.Unknown)
    }
}

private val isBrowser: Boolean
    get() = js("typeof window !== 'undefined' && typeof window.document !== 'undefined'") as Boolean

private val isWebWorker: Boolean
    get() = js("typeof self === 'object' && self.constructor && self.constructor.name === 'DedicatedWorkerGlobalScope'") as Boolean

private val isNode: Boolean
    get() = js("typeof process !== 'undefined' && process.versions != null && process.versions.node != null") as Boolean

private fun detectNodeEnviroment(): UnifiedPlatform.JavaScript {
    val platform = os.platform()

    return UnifiedPlatform.JavaScript(
        tryDetectOsFamily(platform),
        JsEnvironment.Node(
            os.cpus()[0].model,
            os.arch(),
            platform,
            os.type(),
            global.process.version
        )
    )
}

private fun detectBrowserEnvironment(): UnifiedPlatform.JavaScript {
    val userAgent = when {
        isBrowser -> window.navigator.userAgent
        isWebWorker -> js("self").navigator.userAgent as String
        else -> error("Not on a browser environment")
    }

    val result = parse(userAgent)

    return UnifiedPlatform.JavaScript(
        tryDetectOsFamily(result.os.name),
        JsEnvironment.Browser(
            isWebWorker,
            BrowserInfo(result.browser.name, result.browser.version),
            EngineInfo(result.engine.name, result.engine.version),
            PlatformInfo(result.platform.type, result.platform.vendor, result.platform.model),
            OsInfo(result.os.name, result.os.version, result.os.versionName)
        )
    )
}

private fun tryDetectOsFamily(name: String?): OsFamily {
    val map = entriesOf<String, String>(OS_MAP).associate { it.second to it.first }
    val shortName = map[name] ?: return OsFamily.UNKNOWN
    return OsFamily.values().find { it.name.equals(shortName, true) } ?: OsFamily.UNKNOWN
}

fun <K, V> entriesOf(jsObject: dynamic): List<Pair<K, V>> =
    (js("Object.entries") as (dynamic) -> Array<Array<Any?>>)
        .invoke(jsObject)
        .map { entry -> entry[0] as K to entry[1] as V }
