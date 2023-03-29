package com.softartdev.kronos

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform