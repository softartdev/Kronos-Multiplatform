@file:OptIn(ExperimentalTime::class)

package com.softartdev.kronos

import kotlin.time.Clock
import kotlin.time.ExperimentalTime

actual val Clock.Companion.Network: NetworkClock
    get() = WasmNetworkClock

fun NetworkClock.sync() = WasmNetworkClock.sync()
fun NetworkClock.blockingSync(): Boolean = WasmNetworkClock.blockingSync()
suspend fun NetworkClock.awaitSync(): Boolean = WasmNetworkClock.awaitSync()
