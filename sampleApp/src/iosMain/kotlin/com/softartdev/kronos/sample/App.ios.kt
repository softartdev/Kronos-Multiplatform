@file:OptIn(ExperimentalTime::class)

package com.softartdev.kronos.sample

import com.softartdev.kronos.*
import platform.Foundation.NSURL
import platform.UIKit.UIApplication
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

internal actual fun openUrl(url: String?) {
    val nsUrl = url?.let { NSURL.URLWithString(it) } ?: return
    UIApplication.sharedApplication.openURL(nsUrl)
}

internal actual fun clickSync() = Clock.Network.sync()

internal actual fun clickBlockingSync() {
    Clock.Network.blockingSync()
}

internal actual suspend fun clickAwaitSync() {
    Clock.Network.awaitSync()
}