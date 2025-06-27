@file:OptIn(ExperimentalTime::class)

package com.softartdev.kronos.sample

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import com.softartdev.kronos.JvmNetworkClock
import com.softartdev.kronos.Network
import com.softartdev.kronos.sync
import java.awt.Desktop
import java.net.URI
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

internal actual fun openUrl(url: String?) {
    val uri = url?.let { URI.create(it) } ?: return
    Desktop.getDesktop().browse(uri)
}

internal actual fun clickSync() = Clock.Network.sync()

internal actual fun clickBlockingSync() {
    JvmNetworkClock.blockingSync()
}

internal actual suspend fun clickAwaitSync() {
    JvmNetworkClock.awaitSync()
}

@Preview
@Composable
fun AppPreview() = App()