import androidx.compose.ui.window.ComposeUIViewController
import com.softartdev.kronos.sample.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    return ComposeUIViewController { App() }
}
