package com.smartloanadvisornad.data.remote

import com.smartloanadvisornad.data.remote.dto.BaseDto
import retrofit2.http.GET

interface ApiServer {
    @GET ("488/db.json")
    suspend fun getDataDb () : BaseDto
}