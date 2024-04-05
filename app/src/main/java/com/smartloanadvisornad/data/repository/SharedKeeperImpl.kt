package com.smartloanadvisornad.data.repository

import android.app.Application
import android.content.Context
import com.smartloanadvisornad.domain.repository.SharedKeeper
import com.smartloanadvisornad.domain.utils.SHARED_ADDRESS
import com.smartloanadvisornad.domain.utils.SHARED_AMOUNT
import com.smartloanadvisornad.domain.utils.SHARED_APPSFLYER_INSTANCE_ID
import com.smartloanadvisornad.domain.utils.SHARED_DATA
import com.smartloanadvisornad.domain.utils.SHARED_DATE
import com.smartloanadvisornad.domain.utils.SHARED_EMAIL
import com.smartloanadvisornad.domain.utils.SHARED_FIREBASE_TOKEN
import com.smartloanadvisornad.domain.utils.SHARED_FIRST_RUN
import com.smartloanadvisornad.domain.utils.SHARED_MY_TRACKER_INSTANCE_ID
import com.smartloanadvisornad.domain.utils.SHARED_NAME
import com.smartloanadvisornad.domain.utils.SHARED_PHONE
import com.smartloanadvisornad.domain.utils.SHARED_SUB2
import com.smartloanadvisornad.domain.utils.SHARED_YANDEX_DEVICE_ID
import javax.inject.Inject

class SharedKeeperImpl @Inject constructor(
    application: Application
): SharedKeeper {
    private val sharedPref = application.getSharedPreferences(SHARED_DATA, Context.MODE_PRIVATE)

    override suspend fun getFireBaseToken(): String? = sharedPref.getString(SHARED_FIREBASE_TOKEN, "")

    override suspend fun setFireBaseToken(date: String) =
        sharedPref.edit().putString(SHARED_FIREBASE_TOKEN, date).apply()

    override suspend fun getMyTrackerInstanceId(): String? = sharedPref.getString(
        SHARED_MY_TRACKER_INSTANCE_ID, "")

    override suspend fun setMyTrackerInstanceId(date: String) =
        sharedPref.edit().putString(SHARED_MY_TRACKER_INSTANCE_ID, date).apply()

    override suspend fun getAppsFlyerInstanceId(): String? = sharedPref.getString(
        SHARED_APPSFLYER_INSTANCE_ID, "")

    override suspend fun setAppsFlyerInstanceId(date: String) =
        sharedPref.edit().putString(SHARED_APPSFLYER_INSTANCE_ID, date).apply()

    override suspend fun getCurrentDate(): String? = sharedPref.getString(SHARED_DATE, "")

    override suspend fun setCurrentDate(date: String) =
        sharedPref.edit().putString(SHARED_DATE, date).apply()

    override suspend fun setSub2(date: String) =
        sharedPref.edit().putString(SHARED_SUB2, date).apply()

    override suspend  fun getSub2(): String? = sharedPref.getString(SHARED_SUB2, "")

    override suspend fun setYandexMetricaDeviceId(date: String) =
        sharedPref.edit().putString(SHARED_YANDEX_DEVICE_ID, date).apply()

    override suspend fun getYandexMetricaDeviceId(): String? = sharedPref.getString(
        SHARED_YANDEX_DEVICE_ID, "")

    override suspend fun setPhone(date: String) =
        sharedPref.edit().putString(SHARED_PHONE, date).apply()

    override suspend fun getPhone(): String? = sharedPref.getString(SHARED_PHONE, "")
    override suspend fun setName(date: String) {
        sharedPref.edit().putString(SHARED_NAME, date).apply()
    }
    override suspend fun getName(): String?  = sharedPref.getString(SHARED_NAME, "")

    override suspend fun setEmail(date: String) =
        sharedPref.edit().putString(SHARED_EMAIL, date).apply()

    override suspend fun getEmail(): String? = sharedPref.getString(SHARED_EMAIL, "")

    override suspend fun setPeriod(date: Int) =
        sharedPref.edit().putInt(SHARED_ADDRESS, date).apply()

    override suspend fun getPeriod(): Int = sharedPref.getInt(SHARED_ADDRESS, 0)

    override suspend fun setAmount(date: Int) =
        sharedPref.edit().putInt(SHARED_AMOUNT, date).apply()

    override suspend fun getAmount(): Int = sharedPref.getInt(SHARED_AMOUNT, 0)

    override suspend fun setFirstRun(isFirst: Boolean) =
        sharedPref.edit().putBoolean(SHARED_FIRST_RUN, isFirst).apply()

    override suspend fun getFirstRun(): Boolean = sharedPref.getBoolean(SHARED_FIRST_RUN, true)
}