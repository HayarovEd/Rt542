package com.smartloanadvisornad.domain.repository

interface SharedKeeper {

    suspend fun getFireBaseToken(): String?

    suspend fun setFireBaseToken(date: String)

    suspend fun getMyTrackerInstanceId(): String?

    suspend fun setMyTrackerInstanceId(date: String)

    suspend fun getAppsFlyerInstanceId(): String?

    suspend fun setAppsFlyerInstanceId(date: String)

    suspend fun getCurrentDate(): String?

    suspend fun setCurrentDate(date: String)

    suspend fun setSub2(date: String)

    suspend fun getSub2(): String?

    suspend fun setYandexMetricaDeviceId(date: String)

    suspend fun getYandexMetricaDeviceId(): String?
    suspend fun setPhone(date: String)
    suspend fun getPhone(): String?
    suspend fun setName(date: String)
    suspend fun getName(): String?
    suspend fun setEmail(date: String)
    suspend fun getEmail(): String?
    suspend fun setPeriod(date: Int)
    suspend fun getPeriod(): Int?
    suspend fun setAmount(date: Int)
    suspend fun getAmount(): Int?
    suspend fun setFirstRun(isFirst: Boolean)
    suspend fun getFirstRun(): Boolean
}