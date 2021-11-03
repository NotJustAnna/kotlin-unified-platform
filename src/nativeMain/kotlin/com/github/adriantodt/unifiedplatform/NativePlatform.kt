package com.github.adriantodt.unifiedplatform

import kotlin.native.Platform.cpuArchitecture
import kotlin.native.Platform.osFamily
import kotlin.native.CpuArchitecture as NativeCpuArchitecture
import kotlin.native.OsFamily as NativeOsFamily

actual val currentPlatform: UnifiedPlatform
    get() = UnifiedPlatform.Native(osFamily.toUnified(), cpuArchitecture.toUnified())

private fun NativeOsFamily.toUnified(): OsFamily {
    return when (this) {
        NativeOsFamily.UNKNOWN -> OsFamily.UNKNOWN
        NativeOsFamily.MACOSX -> OsFamily.MACOSX
        NativeOsFamily.IOS -> OsFamily.IOS
        NativeOsFamily.LINUX -> OsFamily.LINUX
        NativeOsFamily.WINDOWS -> OsFamily.WINDOWS
        NativeOsFamily.ANDROID -> OsFamily.ANDROID
        NativeOsFamily.WASM -> OsFamily.WASM
        NativeOsFamily.TVOS -> OsFamily.TVOS
        NativeOsFamily.WATCHOS -> OsFamily.WATCHOS
    }
}

private fun NativeCpuArchitecture.toUnified(): CpuArchitecture {
    return when (this) {
        NativeCpuArchitecture.UNKNOWN -> CpuArchitecture.UNKNOWN
        NativeCpuArchitecture.ARM32 -> CpuArchitecture.ARM32
        NativeCpuArchitecture.ARM64 -> CpuArchitecture.ARM64
        NativeCpuArchitecture.X86 -> CpuArchitecture.X86
        NativeCpuArchitecture.X64 -> CpuArchitecture.X64
        NativeCpuArchitecture.MIPS32 -> CpuArchitecture.MIPS32
        NativeCpuArchitecture.MIPSEL32 -> CpuArchitecture.MIPSEL32
        NativeCpuArchitecture.WASM32 -> CpuArchitecture.WASM32
    }
}
