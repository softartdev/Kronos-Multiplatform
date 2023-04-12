package com.softartdev.kronos

import com.lyft.kronos.*

object JvmClockFactory {

    @JvmStatic
    fun createDeviceClock(): Clock = JvmSystemClock()

    @JvmStatic
    @JvmOverloads
    fun createKronosClock(
        syncListener: SyncListener? = null,
        ntpHosts: List<String> = DefaultParam.NTP_HOSTS,
        requestTimeoutMs: Long = DefaultParam.TIMEOUT_MS,
        minWaitTimeBetweenSyncMs: Long = DefaultParam.MIN_WAIT_TIME_BETWEEN_SYNC_MS,
        cacheExpirationMs: Long = DefaultParam.CACHE_EXPIRATION_MS,
        maxNtpResponseTimeMs: Long = DefaultParam.MAX_NTP_RESPONSE_TIME_MS
    ): KronosClock = ClockFactory.createKronosClock(
        localClock = createDeviceClock(),
        syncResponseCache = JvmPreferenceSyncResponseCache,
        syncListener = syncListener,
        ntpHosts = ntpHosts,
        requestTimeoutMs = requestTimeoutMs,
        minWaitTimeBetweenSyncMs = minWaitTimeBetweenSyncMs,
        cacheExpirationMs = cacheExpirationMs,
        maxNtpResponseTimeMs = maxNtpResponseTimeMs
    )
}