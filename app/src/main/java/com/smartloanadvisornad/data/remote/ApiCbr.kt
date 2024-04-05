package com.smartloanadvisornad.data.remote

import com.smartloanadvisornad.data.remote.dto.CbrDto
import retrofit2.http.GET

interface ApiCbr {
    @GET("daily_json.js")
    suspend fun getCbrData () : CbrDto
}