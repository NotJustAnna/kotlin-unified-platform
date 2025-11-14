package net.notjustanna.unifiedplatform.test

import net.notjustanna.unifiedplatform.UnifiedPlatform
import net.notjustanna.unifiedplatform.currentPlatform
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
