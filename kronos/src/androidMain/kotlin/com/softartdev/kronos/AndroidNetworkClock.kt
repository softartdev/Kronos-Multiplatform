package com.softartdev.kronos

import android.content.Context
import com.lyft.kronos.AndroidClockFactory
import com.lyft.kronos.KronosClock
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object AndroidNetworkClock : NetworkClock {
    lateinit var kronosClock: KronosClock

    fun sync(applicationContext: Context) {
        initClock(applicationContext)
        kronosClock.syncInBackground()
    }

    fun blockingSync(applicationContext: Context): Boolean {
        initClock(applicationContext)
        return kronosClock.sync()
    }

    suspend fun awaitSync(applicationContext: Context): Boolean = suspendCoroutine { continuation ->
        initClock(applicationContext)
        try {
            val result = kronosClock.sync()
            continuation.resume(result)
        } catch (e: Exception) {
            continuation.resumeWithException(e)
        }
    }

    private fun initClock(applicationContext: Context) {
        if (::kronosClock.isInitialized) return
        kronosClock = AndroidClockFactory.createKronosClock(applicationContext)
    }

    override fun getCurrentNtpTimeMs(): Long? = when {
        ::kronosClock.isInitialized -> kronosClock.getCurrentTimeMs()
        else -> null
    }
}