@file:OptIn(ExperimentalTime::class)

package com.softartdev.kronos

import kotlin.time.Clock
import kotlin.time.ExperimentalTime

actual val Clock.Companion.Network: NetworkClock
    get() = JvmNetworkClock

fun NetworkClock.sync() = JvmNetworkClock.sync()
fun NetworkClock.blockingSync() = JvmNetworkClock.blockingSync()
suspend fun NetworkClock.awaitSyncSync(): Boolean = JvmNetworkClock.awaitSync()