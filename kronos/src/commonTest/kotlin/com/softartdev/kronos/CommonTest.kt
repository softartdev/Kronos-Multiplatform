@file:OptIn(ExperimentalTime::class)

package com.softartdev.kronos

import kotlin.test.Test
import kotlin.test.assertNull
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

class CommonTest {

    @Test
    fun getCurrentNtpTimeMsTest() = assertNull(actual = Clock.Network.getCurrentNtpTimeMs())
}