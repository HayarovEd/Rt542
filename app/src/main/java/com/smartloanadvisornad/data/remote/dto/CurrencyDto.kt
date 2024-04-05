package com.smartloanadvisornad.data.remote.dto


import com.google.gson.annotations.SerializedName

data class CurrencyDto(
    @SerializedName("CNY")
    val cNY: CNYDto,
    @SerializedName("EUR")
    val eUR: EURDto,
    @SerializedName("USD")
    val uSD: USDDto
)