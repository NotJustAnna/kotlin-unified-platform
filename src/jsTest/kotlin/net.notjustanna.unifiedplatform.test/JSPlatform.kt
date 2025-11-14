package net.notjustanna.unifiedplatform.test

import net.notjustanna.unifiedplatform.UnifiedPlatform
import net.notjustanna.unifiedplatform.currentPlatform
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
