package net.notjustanna.unifiedplatform.js

data class PlatformInfo(val type: String?, val vendor: String?, val model: String?) {
    override fun toString(): String {
        return when {
            type != null -> type
            else -> "<unknown platform>"
        }
    }
}

