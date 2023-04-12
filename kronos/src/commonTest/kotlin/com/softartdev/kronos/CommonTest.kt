package com.softartdev.kronos

import kotlinx.datetime.Clock
import kotlin.test.Test
import kotlin.test.assertNull

class CommonTest {

    @Test
    fun getCurrentNtpTimeMsTest() = assertNull(actual = Clock.Network.getCurrentNtpTimeMs())
}