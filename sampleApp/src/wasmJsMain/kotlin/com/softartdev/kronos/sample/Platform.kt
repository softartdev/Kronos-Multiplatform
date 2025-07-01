package com.softartdev.kronos.sample

class WasmJsPlatform : Platform {
    override val name: String = "Web"
}

actual fun getPlatform(): Platform = WasmJsPlatform()
