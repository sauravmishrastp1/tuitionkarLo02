package com.data.responseModel
import com.google.gson.annotations.SerializedName


data class CityResponse(
    @SerializedName("city")
    var city: List<City>,
    @SerializedName("msg")
    var msg: String,
    @SerializedName("status")
    var status: Int
)

data class City(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String
)