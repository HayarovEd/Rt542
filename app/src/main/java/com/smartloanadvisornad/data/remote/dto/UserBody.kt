package com.smartloanadvisornad.data.remote.dto

import com.google.gson.annotations.SerializedName

data class UserBody(
    @SerializedName("app_id")
    val id: String,
    @SerializedName("first_name")
    val name: String,
    @SerializedName("last_name")
    val surName: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("phone_home")
    val phone: String
)
