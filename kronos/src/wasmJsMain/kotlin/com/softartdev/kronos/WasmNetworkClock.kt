package com.softartdev.kronos

import kotlinx.browser.window
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.await
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.time.Clock

/**
 * Fetches network time using the public WorldTime API and stores the offset
 * from the local system clock. The [sync] functions update the offset
 * asynchronously or in a blocking fashion. After synchronization the clock
 * provides network based timestamps via [getCurrentNtpTimeMs].
 */
object WasmNetworkClock : NetworkClock {

    private var offsetMs: Long? = null

    fun sync() {
        GlobalScope.launch { awaitSync() }
    }

    fun blockingSync(): Boolean = runBlocking { awaitSync() }

    suspend fun awaitSync(): Boolean = try {
        val response = window.fetch("https://worldtimeapi.org/api/ip").await()
        val json = response.json().await() as dynamic
        val dateStr = json.datetime as String
        val networkTimeMs = js("Date.parse(dateStr)").unsafeCast<Double>().toLong()
        val systemTimeMs = js("Date.now()").unsafeCast<Double>().toLong()
        offsetMs = networkTimeMs - systemTimeMs
        true
    } catch (t: Throwable) {
        console.error("Failed to sync time", t)
        false
    }

    override fun getCurrentNtpTimeMs(): Long? {
        val offset = offsetMs ?: return null
        val systemTimeMs = js("Date.now()").unsafeCast<Double>().toLong()
        return systemTimeMs + offset
    }
}
