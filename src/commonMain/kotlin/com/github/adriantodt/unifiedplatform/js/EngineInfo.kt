package com.github.adriantodt.unifiedplatform.js

data class EngineInfo(val name: String?, val version: String?) {
    override fun toString(): String {
        return when {
            name != null && version != null -> "$name v$version"
            name != null && version == null -> name
            else -> "<unknown engine>"
        }
    }
}
