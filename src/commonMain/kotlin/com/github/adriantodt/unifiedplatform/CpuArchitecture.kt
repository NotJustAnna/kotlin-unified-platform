package com.github.adriantodt.unifiedplatform

public enum class CpuArchitecture(val bitness: Int) {
    UNKNOWN(-1),
    ARM32(32),
    ARM64(64),
    X86(32),
    X64(64),
    MIPS32(32),
    MIPSEL32(32),
    WASM32(32);
}
