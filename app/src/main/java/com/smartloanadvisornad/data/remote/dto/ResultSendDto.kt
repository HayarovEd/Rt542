package com.smartloanadvisornad.data.remote.dto


import com.google.gson.annotations.SerializedName

data class ResultSendDto(
    @SerializedName("result")
    val result: String,
    @SerializedName("user_info_id")
    val userInfoId: Int
)