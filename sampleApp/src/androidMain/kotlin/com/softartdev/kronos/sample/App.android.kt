@file:OptIn(ExperimentalTime::class)

package com.softartdev.kronos.sample

import android.app.Application
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.softartdev.kronos.*
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

class AndroidApp : Application() {
    companion object {
        lateinit var INSTANCE: AndroidApp
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        Napier.base(DebugAntilog())
        Clock.Network.sync(applicationContext)
    }
}

class AppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { App() }
    }
}

internal actual fun openUrl(url: String?) {
    val uri: Uri = url?.let(Uri::parse) ?: return
    val intent: Intent = Intent().apply {
        action = Intent.ACTION_VIEW
        data = uri
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    AndroidApp.INSTANCE.startActivity(intent)
}

internal actual fun clickSync() = Clock.Network.sync(context = AndroidApp.INSTANCE)

internal actual fun clickBlockingSync() {
    Clock.Network.blockingSync(context = AndroidApp.INSTANCE)
}

internal actual suspend fun clickAwaitSync() {
    Clock.Network.awaitSync(ctx = AndroidApp.INSTANCE)
}