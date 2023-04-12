package com.softartdev.kronos

import android.content.Context
import kotlinx.datetime.Clock

actual val Clock.Companion.Network: NetworkClock
    get() = AndroidNetworkClock

fun NetworkClock.sync(context: Context) = AndroidNetworkClock.sync(context)
fun NetworkClock.blockingSync(context: Context) = AndroidNetworkClock.blockingSync(context)
suspend fun NetworkClock.awaitSync(ctx: Context): Boolean = AndroidNetworkClock.awaitSync(ctx)