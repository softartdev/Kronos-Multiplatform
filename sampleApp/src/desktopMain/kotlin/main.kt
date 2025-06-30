@file:OptIn(ExperimentalTime::class)

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.softartdev.kronos.Network
import com.softartdev.kronos.sample.App
import com.softartdev.kronos.sync
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

fun main() {
    Napier.base(DebugAntilog())

    Clock.Network.sync()

    application {
        Window(
            title = "Kronos Multiplatform",
            state = rememberWindowState(width = 600.dp, height = 400.dp),
            onCloseRequest = ::exitApplication,
        ) { App() }
    }
}