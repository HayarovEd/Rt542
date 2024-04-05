package com.smartloanadvisornad.data.remote.dto


import com.google.gson.annotations.SerializedName

data class BaseDto(
    @SerializedName("app_config")
    val appConfigDto: AppConfigDto,
    @SerializedName("loans")
    val loanDtos: List<LoanDto>
)