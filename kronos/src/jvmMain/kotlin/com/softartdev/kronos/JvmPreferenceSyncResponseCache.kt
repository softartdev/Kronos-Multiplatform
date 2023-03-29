package com.softartdev.kronos

import com.lyft.kronos.SyncResponseCache
import com.lyft.kronos.internal.Constants.TIME_UNAVAILABLE
import java.util.prefs.Preferences

class JvmPreferenceSyncResponseCache : SyncResponseCache {
    private val preferences: Preferences = Preferences.userNodeForPackage(JvmPreferenceSyncResponseCache::class.java)

    override var currentTime: Long
        get() = preferences.getLong(KEY_CURRENT_TIME, TIME_UNAVAILABLE)
        set(value) = preferences.putLong(KEY_CURRENT_TIME, value)
    override var elapsedTime: Long
        get() = preferences.getLong(KEY_ELAPSED_TIME, TIME_UNAVAILABLE)
        set(value) = preferences.putLong(KEY_ELAPSED_TIME, value)
    override var currentOffset: Long
        get() = preferences.getLong(KEY_OFFSET, TIME_UNAVAILABLE)
        set(value) = preferences.putLong(KEY_OFFSET, value)

    override fun clear() = preferences.clear()

    companion object {
        internal const val KEY_CURRENT_TIME = "com.lyft.kronos.cached_current_time"
        internal const val KEY_ELAPSED_TIME = "com.lyft.kronos.cached_elapsed_time"
        internal const val KEY_OFFSET = "com.lyft.kronos.cached_offset"
    }
}