@file:OptIn(ExperimentalTime::class)

package com.softartdev.kronos.sample

import kotlinx.browser.window
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

internal actual fun openUrl(url: String?) {
    url ?: return
    window.open(url, "_blank")
}

internal actual fun clickSync() = Clock.Network.sync()

internal actual fun clickBlockingSync() {
    Clock.Network.blockingSync()
}

internal actual suspend fun clickAwaitSync() {
    Clock.Network.awaitSync()
}
