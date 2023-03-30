import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.softartdev.kronos.NetworkClock
import com.softartdev.kronos.sample.App
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

fun main() {
    Napier.base(DebugAntilog())

    NetworkClock.sync()

    application {
        Window(
            title = "Kronos Multiplatform",
            state = rememberWindowState(width = 600.dp, height = 400.dp),
            onCloseRequest = ::exitApplication,
        ) { App() }
    }
}