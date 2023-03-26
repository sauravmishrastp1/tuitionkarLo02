package com.data.responseModel
import com.google.gson.annotations.SerializedName


data class ApplideJobSucessResponse(
    @SerializedName("msg")
    val msg: String,
    @SerializedName("status")
    val status: Int
)