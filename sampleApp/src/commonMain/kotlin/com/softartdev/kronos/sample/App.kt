package com.softartdev.kronos.sample

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.softartdev.kronos.Greeting

@Composable
internal fun App() = AppTheme {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = { openUrl("https://github.com/terrakok") }
        ) {
            Text("Open github")
        }
        Spacer(modifier = Modifier.height(32.dp))
        Text(text = Greeting().greet())
    }
}

internal expect fun openUrl(url: String?)