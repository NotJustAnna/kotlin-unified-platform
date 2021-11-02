package com.github.adriantodt.unifiedplatform

import org.apache.commons.lang3.SystemUtils
import org.apache.commons.lang3.JavaVersion as CommonsJavaVersion

actual val currentPlatform: UnifiedPlatform
    get() = detectJavaPlatform()

private fun detectJavaPlatform(): UnifiedPlatform.Java {
    val osFamily = detectOsFamily()
    val version = detectJavaVersion()
    return UnifiedPlatform.Java(osFamily, version)
}

private fun detectOsFamily(): OsFamily {
    return when {
        SystemUtils.JAVA_SPECIFICATION_VENDOR == "The Android Project" -> OsFamily.ANDROID
        SystemUtils.IS_OS_LINUX -> OsFamily.LINUX
        SystemUtils.IS_OS_MAC_OSX -> OsFamily.MACOSX
        SystemUtils.IS_OS_WINDOWS -> OsFamily.WINDOWS
        else -> OsFamily.UNKNOWN
    }
}

private val ignore = listOf(CommonsJavaVersion.JAVA_0_9, CommonsJavaVersion.JAVA_RECENT)

private fun detectJavaVersion(): JavaVersion {
    return when (CommonsJavaVersion.values().filterNot(ignore::contains).last(SystemUtils::isJavaVersionAtLeast)) {
        CommonsJavaVersion.JAVA_1_6 -> JavaVersion.JAVA_1_6
        CommonsJavaVersion.JAVA_1_7 -> JavaVersion.JAVA_1_7
        CommonsJavaVersion.JAVA_1_8 -> JavaVersion.JAVA_1_8
        CommonsJavaVersion.JAVA_9 -> JavaVersion.JAVA_9
        CommonsJavaVersion.JAVA_10 -> JavaVersion.JAVA_10
        CommonsJavaVersion.JAVA_11 -> JavaVersion.JAVA_11
        CommonsJavaVersion.JAVA_12 -> JavaVersion.JAVA_12
        CommonsJavaVersion.JAVA_13 -> JavaVersion.JAVA_13
        CommonsJavaVersion.JAVA_14 -> JavaVersion.JAVA_14
        CommonsJavaVersion.JAVA_15 -> JavaVersion.JAVA_15
        CommonsJavaVersion.JAVA_16 -> JavaVersion.JAVA_16
        CommonsJavaVersion.JAVA_17 -> JavaVersion.JAVA_17
        else -> JavaVersion.UNKNOWN
    }
}
