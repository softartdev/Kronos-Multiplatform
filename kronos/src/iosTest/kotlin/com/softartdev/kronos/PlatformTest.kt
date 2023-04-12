package com.softartdev.kronos

import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withTimeout
import kotlinx.datetime.Clock
import kotlin.test.*

class PlatformTest {

    @Ignore // TODO: fix
    @Test
    fun getCurrentNtpTimeMsTest() = runTest {
        assertNull(Clock.Network.getCurrentNtpTimeMs())
        assertFailsWith<TimeoutCancellationException> {
            withTimeout(timeMillis = 20_000) {
                Clock.Network.awaitSync()
            }
        }
        assertNotNull(Clock.Network.getCurrentNtpTimeMs())
    }
}