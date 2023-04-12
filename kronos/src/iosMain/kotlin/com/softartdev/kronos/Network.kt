package com.softartdev.kronos

import kotlinx.datetime.Clock
import platform.Foundation.NSDate

actual val Clock.Companion.Network: NetworkClock
    get() = IosNetworkClock

fun NetworkClock.sync() = IosNetworkClock.sync()
fun NetworkClock.blockingSync() = IosNetworkClock.blockingSync()
suspend fun NetworkClock.awaitSync(): NSDate? = IosNetworkClock.awaitSync()