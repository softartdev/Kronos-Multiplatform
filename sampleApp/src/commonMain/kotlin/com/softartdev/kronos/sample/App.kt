package com.softartdev.kronos.sample

import androidx.compose.runtime.Composable

@Composable
internal fun App() = AppTheme {
    TickScreen()
}

internal expect fun openUrl(url: String?)

internal expect fun clickSync()

internal expect fun clickBlockingSync()

internal expect suspend fun clickAwaitSync()