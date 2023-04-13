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

    @Ignore
    @Test
    fun awaitSyncTest() = runTest {
        val currentNtpTimeMs = Clock.Network.getCurrentNtpTimeMs()
        println("⏺️ before sync: $currentNtpTimeMs")
        assertNull(currentNtpTimeMs)

        Clock.Network.awaitSync()

        val instance = Clock.Network.now()
        println("⏺️ after sync: $instance")
        assertNotNull(instance)
    }
}