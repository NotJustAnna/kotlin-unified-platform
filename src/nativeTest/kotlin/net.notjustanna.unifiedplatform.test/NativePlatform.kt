package net.notjustanna.unifiedplatform.test

import net.notjustanna.unifiedplatform.UnifiedPlatform
import net.notjustanna.unifiedplatform.currentPlatform
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
