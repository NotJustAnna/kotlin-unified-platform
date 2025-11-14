@file:JsModule("bowser")
@file:JsNonModule

package Bowser

external fun parse(userAgent: String): Parser.ParsedResult

external val OS_MAP: dynamic

external object Parser {
    interface ParsedResult {
        val browser: BrowserDetails
        val os: OsDetails
        val platform: PlatformDetails
        val engine: EngineDetails
    }

    interface BrowserDetails {
        val name: String?
        val version: String?
    }

    interface OsDetails {
        val name: String?
        val version: String?
        val versionName: String?
    }

    interface PlatformDetails {
        val type: String?
        val vendor: String?
        val model: String?
    }

    interface EngineDetails {
        val name: String?
        val version: String?
    }
}
