package com.softartdev.kronos

import com.lyft.kronos.KronosClock
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object JvmNetworkClock : NetworkClock {
    private val lazyProp: Lazy<KronosClock> = lazy(initializer = JvmClockFactory::createKronosClock)
    private val kronosClock: KronosClock by lazyProp

    fun sync() = kronosClock.syncInBackground()

    fun blockingSync(): Boolean = kronosClock.sync()

    suspend fun awaitSync(): Boolean = suspendCoroutine { continuation ->
        try {
            val result = kronosClock.sync()
            continuation.resume(result)
        } catch (e: Exception) {
            continuation.resumeWithException(e)
        }
    }

    override fun getCurrentNtpTimeMs(): Long? = when {
        lazyProp.isInitialized() -> kronosClock.getCurrentTimeMs()
        else -> null
    }
}