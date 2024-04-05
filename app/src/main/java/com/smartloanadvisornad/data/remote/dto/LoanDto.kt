package com.smartloanadvisornad.data.remote.dto


import com.google.gson.annotations.SerializedName

data class LoanDto(
    @SerializedName("description")
    val description: String,
    @SerializedName("hide_PercentFields")
    val hidePercentFields: String,
    @SerializedName("hide_TermFields")
    val hideTermFields: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("isActive")
    val isActive: String,
    @SerializedName("itemId")
    val itemId: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("order")
    val order: String,
    @SerializedName("orderButtonText")
    val orderButtonText: String,
    @SerializedName("percent")
    val percent: String,
    @SerializedName("percentPostfix")
    val percentPostfix: String,
    @SerializedName("percentPrefix")
    val percentPrefix: String,
    @SerializedName("position")
    val position: Int,
    @SerializedName("score")
    val score: String,
    @SerializedName("screen")
    val screen: String,
    @SerializedName("show_cash")
    val showCash: String,
    @SerializedName("show_mastercard")
    val showMastercard: String,
    @SerializedName("show_mir")
    val showMir: String,
    @SerializedName("show_qiwi")
    val showQiwi: String,
    @SerializedName("show_visa")
    val showVisa: String,
    @SerializedName("show_yandex")
    val showYandex: String,
    @SerializedName("summMax")
    val summMax: String,
    @SerializedName("summMid")
    val summMid: String,
    @SerializedName("summMin")
    val summMin: String,
    @SerializedName("summPostfix")
    val summPostfix: String,
    @SerializedName("summPrefix")
    val summPrefix: String
)