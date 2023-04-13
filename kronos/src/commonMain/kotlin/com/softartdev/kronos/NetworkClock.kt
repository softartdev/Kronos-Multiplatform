package com.softartdev.kronos

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

interface NetworkClock : Clock {
    /**
     * @return the current time in milliseconds, or null if no ntp sync has occurred.
     */
    fun getCurrentNtpTimeMs(): Long?

    /**
     * Returns the [Instant] corresponding to the current time, according to this clock.
     */
    override fun now(): Instant {
        val currentNtpTimeMs = getCurrentNtpTimeMs()
        requireNotNull(currentNtpTimeMs) { "No ntp sync has occurred" }
        return Instant.fromEpochMilliseconds(currentNtpTimeMs)
    }
}