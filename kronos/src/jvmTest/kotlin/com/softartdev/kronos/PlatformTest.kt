package com.softartdev.kronos

import kotlinx.datetime.Clock
import org.junit.Ignore
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class PlatformTest {

    @Ignore // TODO: fix on CI
    @Test
    fun getCurrentNtpTimeMsTest() {
        assertNull(Clock.Network.getCurrentNtpTimeMs())
        assertTrue(Clock.Network.blockingSync())
        assertNotNull(Clock.Network.getCurrentNtpTimeMs())
    }
}