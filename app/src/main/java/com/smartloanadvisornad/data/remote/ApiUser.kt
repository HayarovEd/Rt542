package com.smartloanadvisornad.data.remote

import com.smartloanadvisornad.data.remote.dto.ResultSendDto
import com.smartloanadvisornad.data.remote.dto.UserBody
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiUser {
    @POST("philippines")
    suspend fun postUser(@Body userBody: UserBody): ResultSendDto
}