package com.data.responseModel
import com.google.gson.annotations.SerializedName


data class AllCountryResponse(
    @SerializedName("country")
    var country: List<Country>,
    @SerializedName("msg")
    var msg: String,
    @SerializedName("status")
    var status: Int
)

data class Country(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String
)