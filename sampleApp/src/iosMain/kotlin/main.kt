import androidx.compose.ui.window.ComposeUIViewController
import com.softartdev.kronos.sample.App
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import platform.UIKit.UIViewController

fun debugBuild() {
    Napier.base(DebugAntilog())
}

fun MainViewController(): UIViewController {
    return ComposeUIViewController { App() }
}
