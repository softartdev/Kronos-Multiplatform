package com.softartdev.kronos

import kotlinx.datetime.Clock
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class PlatformTest {

    @Test
    fun getCurrentNtpTimeMsTest() {
        assertNull(Clock.Network.getCurrentNtpTimeMs())
        assertTrue(Clock.Network.blockingSync())
        assertNotNull(Clock.Network.getCurrentNtpTimeMs())
    }
}