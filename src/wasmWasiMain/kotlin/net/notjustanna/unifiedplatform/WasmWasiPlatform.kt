package net.notjustanna.unifiedplatform

actual val currentPlatform: UnifiedPlatform = UnifiedPlatform.WebAssembly(WasmTarget.WASI)
