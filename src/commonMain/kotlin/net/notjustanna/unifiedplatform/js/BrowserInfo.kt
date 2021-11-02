package net.notjustanna.unifiedplatform.js

data class BrowserInfo(val name: String?, val version: String?) {
    override fun toString(): String {
        return when {
            name != null && version != null -> "$name v$version"
            name != null && version == null -> name
            else -> "<unknown browser>"
        }
    }
}
