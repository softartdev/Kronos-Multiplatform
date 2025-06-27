@file:OptIn(ExperimentalTime::class)

package com.softartdev.kronos

import platform.Foundation.NSDate
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

actual val Clock.Companion.Network: NetworkClock
    get() = IosNetworkClock

fun NetworkClock.sync() = IosNetworkClock.sync()
fun NetworkClock.blockingSync() = IosNetworkClock.blockingSync()
suspend fun NetworkClock.awaitSync(): NSDate? = IosNetworkClock.awaitSync()