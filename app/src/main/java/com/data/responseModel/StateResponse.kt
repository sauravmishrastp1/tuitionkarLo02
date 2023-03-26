package com.data.responseModel
import com.google.gson.annotations.SerializedName


data class StateResponse(
    @SerializedName("msg")
    var msg: String,
    @SerializedName("state")
    var state: List<State>,
    @SerializedName("status")
    var status: Int
)

data class State(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String
)