package com.softartdev.kronos.sample

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform