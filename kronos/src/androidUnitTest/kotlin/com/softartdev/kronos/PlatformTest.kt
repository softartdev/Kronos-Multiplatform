@file:OptIn(ExperimentalTime::class)

package com.softartdev.kronos

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import kotlin.test.*
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

class PlatformTest {

    @Ignore // TODO: fix
    @Test
    fun getCurrentNtpTimeMsTest() {
        assertNull(Clock.Network.getCurrentNtpTimeMs())
        val appContext: Context = InstrumentationRegistry.getInstrumentation().targetContext
        assertTrue(Clock.Network.blockingSync(context = appContext))
        assertNotNull(Clock.Network.getCurrentNtpTimeMs())
    }
}