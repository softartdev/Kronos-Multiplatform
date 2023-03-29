package com.softartdev.kronos

import android.content.Context
import com.lyft.kronos.AndroidClockFactory
import com.lyft.kronos.KronosClock

actual object NetworkClock {

    lateinit var kronosClock: KronosClock

    fun sync(applicationContext: Context) {
        kronosClock = AndroidClockFactory.createKronosClock(applicationContext)
        kronosClock.syncInBackground()
    }

    actual fun getCurrentNtpTimeMs(): Long? = when {
        ::kronosClock.isInitialized -> kronosClock.getCurrentTimeMs()
        else -> null
    }
}