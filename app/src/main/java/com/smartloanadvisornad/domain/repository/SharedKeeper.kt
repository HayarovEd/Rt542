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
    suspend fun setEmail(date: String)
    suspend fun getEmail(): String?
    suspend fun setAddress(date: String)
    suspend fun getAddress(): String?
    suspend fun setAmount(date: String)
    suspend fun getAmount(): String?
    suspend fun setFirstRun(isFirst: Boolean)
    suspend fun getFirstRun(): Boolean
}