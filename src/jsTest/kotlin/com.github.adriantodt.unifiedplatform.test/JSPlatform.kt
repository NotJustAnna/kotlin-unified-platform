package com.github.adriantodt.unifiedplatform.test

import com.github.adriantodt.unifiedplatform.UnifiedPlatform
import com.github.adriantodt.unifiedplatform.currentPlatform
import kotlin.test.Test
import kotlin.test.assertIs

class JSPlatform {
    @Test
    fun testJSPlatform() {
        val platform = currentPlatform
        assertIs<UnifiedPlatform.JavaScript>(platform)
        println(platform)
    }
}
