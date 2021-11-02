package com.github.adriantodt.unifiedplatform.test

import com.github.adriantodt.unifiedplatform.UnifiedPlatform
import com.github.adriantodt.unifiedplatform.currentPlatform
import kotlin.test.Test
import kotlin.test.assertIs

class JVMPlatform {
    @Test
    fun testJVMPlatform() {
        val platform = currentPlatform
        assertIs<UnifiedPlatform.Java>(platform)
        println(platform)
    }
}
