package com.softartdev.kronos

actual object NetworkClock {

    fun sync() {
        //TODO "Not yet implemented"
    }

    actual fun getCurrentNtpTimeMs(): Long? = null
}