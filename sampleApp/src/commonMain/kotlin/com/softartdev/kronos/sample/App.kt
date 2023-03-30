package com.softartdev.kronos.sample

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.softartdev.kronos.Greeting
import com.softartdev.kronos.NetworkClock
import io.github.aakira.napier.Napier
import kotlinx.datetime.Clock

@Composable
internal fun App() = AppTheme {
    var ntpTimeMs by remember { mutableStateOf(NetworkClock.getCurrentNtpTimeMs()) }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = "Network time millis: $ntpTimeMs")
        Button(
            onClick = { ntpTimeMs = logNtpTimeMs() }
        ) {
            Text(text = "Refresh network time millis")
        }
        Button(
            onClick = { openUrl("https://github.com/softartdev/Kronos-Multiplatform") }
        ) {
            Text("Open github")
        }
        Text(text = Greeting().greet())
    }
}

private fun logNtpTimeMs(): Long? {
    val networkTimeMs = NetworkClock.getCurrentNtpTimeMs()
    Napier.d(tag = "⌚️", message = "Network time millis: $networkTimeMs")
    val systemTimeMs = Clock.System.now().toEpochMilliseconds()
    Napier.d(tag = "⌚️", message = "System time millis: $systemTimeMs")
    val diff = networkTimeMs?.minus(systemTimeMs)
    Napier.d(tag = "⌚️", message = "Diff: $diff")
    return networkTimeMs
}

internal expect fun openUrl(url: String?)