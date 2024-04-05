package com.smartloanadvisornad.data.remote.dto

import com.google.gson.annotations.SerializedName

data class FolderPathDto(
    @SerializedName("actualbackend")
    val folder: String
)
