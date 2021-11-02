package net.notjustanna.unifiedplatform.js

data class OsInfo(val name: String?, val version: String?, val versionName: String?) {
    override fun toString(): String {
        if (name != null) return name.lowercase()
        return "<unknown operating system>"
    }
}
