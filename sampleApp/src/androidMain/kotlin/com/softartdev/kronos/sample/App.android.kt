package com.softartdev.kronos.sample

import android.app.Application
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.softartdev.kronos.*
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import kotlinx.datetime.Clock

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
        setContent { App() }
    }
}

internal actual fun openUrl(url: String?) {
    val uri = url?.let { Uri.parse(it) } ?: return
    val intent = Intent().apply {
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