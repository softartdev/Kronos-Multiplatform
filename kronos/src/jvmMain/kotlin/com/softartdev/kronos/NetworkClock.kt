package com.softartdev.kronos

import com.lyft.kronos.KronosClock

actual object NetworkClock {

    lateinit var kronosClock: KronosClock

    fun sync() {
        kronosClock = JvmClockFactory.createKronosClock()
        kronosClock.syncInBackground()
    }

    actual fun getCurrentNtpTimeMs(): Long? = when {
        ::kronosClock.isInitialized -> kronosClock.getCurrentTimeMs()
        else -> null
    }
}