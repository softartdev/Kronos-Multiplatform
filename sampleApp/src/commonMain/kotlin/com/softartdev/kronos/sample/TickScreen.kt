package com.softartdev.kronos.sample

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Scaffold
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import com.softartdev.kronos.Network
import io.github.aakira.napier.Napier
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

@Composable
fun TickScreen() = Scaffold(topBar = {
    TopAppBar(title = { Text(text = Greeting().greet()) })
}, content = { paddingValues ->
    Column(
        modifier = Modifier.padding(
            vertical = paddingValues.calculateTopPadding()
                .coerceAtLeast(minimumValue = 8.dp),
            horizontal = paddingValues.calculateStartPadding(LocalLayoutDirection.current)
                .coerceAtLeast(minimumValue = 8.dp),
        ),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        var networkTime: Instant? by remember { mutableStateOf(value = clockNetworkNowOrNull()) }
        Text(text = "Network time:", style = typography.subtitle2)
        Text(text = networkTime.toString(), style = typography.body1)
        var systemTime: Instant by remember { mutableStateOf(Clock.System.now()) }
        Text(text = "System time:", style = typography.subtitle2)
        Text(
            text = systemTime.toLocalDateTime(TimeZone.currentSystemDefault()).toString(),
            style = typography.body1
        )
        var diff: Duration? by remember {
            mutableStateOf(value = networkTime?.let { it - systemTime })
        }
        Text(text = "Diff:", style = typography.subtitle2)
        Text(text = diff.toString(), style = typography.body1)
        var ticking by remember { mutableStateOf(false) }
        val synced by remember { derivedStateOf { networkTime != null } }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = "Ticking: ", style = typography.subtitle1)
            Switch(checked = ticking, onCheckedChange = { ticking = it })
            Text(text = "Synced: ", style = typography.subtitle1)
            Checkbox(checked = synced, onCheckedChange = null, enabled = false)
        }
        fun tick() {
            systemTime = Clock.System.now()
            networkTime = clockNetworkNowOrNull()
            diff = networkTime?.let { it - systemTime }
            Napier.d(tag = "⏳", message = "Ticking Diff: $diff")
        }
        if (ticking) {
            LaunchedEffect(key1 = "tick") {
                while (ticking) {
                    tick()
                    delay(1.seconds)
                }
            }
        }
        val loadingState: MutableState<Boolean> = remember { mutableStateOf(false) }
        val coroutineScope = rememberCoroutineScope()
        if (loadingState.value) {
            CircularProgressIndicator()
        } else {
            Button(onClick = {
                coroutineScope.launch {
                    loadingState.value = true
                    clickAwaitSync()
                    tick()
                    loadingState.value = false
                }
            }) {
                Text(text = "Sync network time")
            }
        }
        Button(onClick = { openUrl("https://github.com/softartdev/Kronos-Multiplatform") }) {
            Text("Open github")
        }
    }
})

private fun clockNetworkNowOrNull(): Instant? = try {
    Clock.Network.now()
} catch (e: Exception) {
    Napier.e(tag = "❌", throwable = e, message = "Network time error")
    null
}
