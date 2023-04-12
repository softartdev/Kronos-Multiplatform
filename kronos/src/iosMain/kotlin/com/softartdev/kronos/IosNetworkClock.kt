package com.softartdev.kronos

import platform.Foundation.NSDate
import platform.Foundation.NSNumber
import platform.Foundation.timeIntervalSince1970
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object IosNetworkClock : NetworkClock {

    fun sync() = Kronos.sync()

    fun blockingSync() = Kronos.blockingSync()

    suspend fun awaitSync(): NSDate? = suspendCoroutine { continuation: Continuation<NSDate?> ->
        try {
            Kronos.syncWithCallback { nsDate: NSDate?, _: NSNumber? -> continuation.resume(nsDate) }
        } catch (t: Throwable) {
            continuation.resumeWithException(t)
        }
    }

    override fun getCurrentNtpTimeMs(): Long? {
        val nsDate: NSDate? = Kronos.now()
        return nsDate?.timeIntervalSince1970?.toLong()
    }
}