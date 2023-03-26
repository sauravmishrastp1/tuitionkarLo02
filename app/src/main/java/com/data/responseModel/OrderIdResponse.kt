package com.data.responseModel
import com.google.gson.annotations.SerializedName


data class OrderIdResponse(
    @SerializedName("amount")
    var amount: String,
    @SerializedName("msg")
    var msg: String,
    @SerializedName("order_id")
    var orderId: String,
    @SerializedName("status")
    var status: Int
)