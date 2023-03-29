package com.softartdev.kronos.sample

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import java.awt.Desktop
import java.net.URI

internal actual fun openUrl(url: String?) {
    val uri = url?.let { URI.create(it) } ?: return
    Desktop.getDesktop().browse(uri)
}

@Preview
@Composable
fun AppPreview() = App()