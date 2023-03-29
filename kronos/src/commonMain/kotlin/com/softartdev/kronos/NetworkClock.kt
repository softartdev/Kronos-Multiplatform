package com.softartdev.kronos

expect object NetworkClock {
    /**
     * @return the current time in milliseconds, or null if no ntp sync has occurred.
     */
    fun getCurrentNtpTimeMs(): Long?
}