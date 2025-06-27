@file:OptIn(ExperimentalTime::class)

package com.softartdev.kronos

import kotlin.time.Clock
import kotlin.time.ExperimentalTime

expect val Clock.Companion.Network: NetworkClock