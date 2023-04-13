package com.softartdev.kronos.sample

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.softartdev.kronos.Network
import io.github.aakira.napier.Napier
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlin.time.Duration

@Composable
internal fun App() = AppTheme {
    var diffDuration by remember { mutableStateOf(calcDiffDuration()) }
    val loadingState: MutableState<Boolean> = remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = "Difference duration between system and network time:\n$diffDuration")
        Button(onClick = { diffDuration = calcDiffDuration() }) {
            Text(text = "Refresh difference duration")
        }
        Button(onClick = ::clickSync) {
            Text(text = "Sync network time async")
        }
        if (loadingState.value) {
            CircularProgressIndicator()
        } else {
            Button(onClick = {
                coroutineScope.launch {
                    loadingState.value = true
                    clickAwaitSync()
                    loadingState.value = false
                }
            }) {
                Text(text = "Sync network time blocking")
            }
        }
        Button(onClick = { openUrl("https://github.com/softartdev/Kronos-Multiplatform") }) {
            Text("Open github")
        }
        Text(text = Greeting().greet())
    }
}

internal expect fun openUrl(url: String?)

internal expect fun clickSync()

internal expect fun clickBlockingSync()

internal expect suspend fun clickAwaitSync()

private fun calcDiffDuration(): Duration {
    val sysInstant = Clock.System.now()
    Napier.d(tag = "⌚️", message = "System time: $sysInstant")
    val netInstant = Clock.Network.now()
    Napier.d(tag = "⌚️", message = "Network time: $netInstant")
    val diff: Duration = netInstant - sysInstant
    Napier.d(tag = "⌚️", message = "Diff: $diff")
    return diff
}