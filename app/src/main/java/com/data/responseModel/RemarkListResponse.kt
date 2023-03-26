package com.data.responseModel
import com.google.gson.annotations.SerializedName


data class RemarkListResponse(
    @SerializedName("msg")
    val msg: String,
    @SerializedName("remark_listing")
    val remarkListing: List<RemarkListing>,
    @SerializedName("status")
    val status: Int
)

data class RemarkListing(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("deleted_at")
    val deletedAt: Any,
    @SerializedName("id")
    val id: Int,
    @SerializedName("is_deleted")
    val isDeleted: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("time")
    val time: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("user_id")
    val userId: String
)