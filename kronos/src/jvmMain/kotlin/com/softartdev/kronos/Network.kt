package com.softartdev.kronos

import kotlinx.datetime.Clock

actual val Clock.Companion.Network: NetworkClock
    get() = JvmNetworkClock

fun NetworkClock.sync() = JvmNetworkClock.sync()
fun NetworkClock.blockingSync() = JvmNetworkClock.blockingSync()
suspend fun NetworkClock.awaitSyncSync(): Boolean = JvmNetworkClock.awaitSync()