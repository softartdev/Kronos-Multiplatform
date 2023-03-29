package com.softartdev.kronos

import com.lyft.kronos.Clock

class JvmSystemClock : Clock {
    override fun getCurrentTimeMs(): Long = System.currentTimeMillis()

    // TODO: use SystemClock.elapsedRealtime()
    override fun getElapsedTimeMs(): Long = System.currentTimeMillis()
}