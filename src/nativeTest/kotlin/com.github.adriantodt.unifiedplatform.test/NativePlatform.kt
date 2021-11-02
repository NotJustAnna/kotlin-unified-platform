package com.github.adriantodt.unifiedplatform.test

import com.github.adriantodt.unifiedplatform.UnifiedPlatform
import com.github.adriantodt.unifiedplatform.currentPlatform
import kotlin.test.Test
import kotlin.test.assertIs

class NativePlatform {
    @Test
    fun testNativePlatform() {
        val platform = currentPlatform
        assertIs<UnifiedPlatform.Native>(platform)
        println(platform)
    }
}
