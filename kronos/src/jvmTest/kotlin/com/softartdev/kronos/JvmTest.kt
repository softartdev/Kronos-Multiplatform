package com.softartdev.kronos

import org.junit.Assert.assertTrue
import org.junit.Test

class JvmTest {

    @Test
    fun testExample() {
        assertTrue("Check Jvm is mentioned", Greeting().greet().contains("JVM"))
    }
}