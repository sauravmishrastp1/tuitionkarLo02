package com.data.responseModel
import com.google.gson.annotations.SerializedName


data class SubscriptionResponse(
    @SerializedName("msg")
    var msg: String,
    @SerializedName("our_plan")
    var ourPlan: List<OurPlan>,
    @SerializedName("plan_expired_date")
    var planExpiredDate: String,
    @SerializedName("profile")
    var profile: Profile,
    @SerializedName("status")
    var status: Int
)

data class JobSeekerPaymentHistory(
    @SerializedName("amount")
    var amount: String,
    @SerializedName("created_at")
    var createdAt: String,
    @SerializedName("expired_at")
    var expiredAt: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("is_expired")
    var isExpired: String,
    @SerializedName("order_id")
    var orderId: String,
    @SerializedName("package_id")
    var packageId: String,
    @SerializedName("payment_id")
    var paymentId: Any,
    @SerializedName("payment_mode")
    var paymentMode: String,
    @SerializedName("payment_request_id")
    var paymentRequestId: String,
    @SerializedName("payment_status")
    var paymentStatus: String,
    @SerializedName("response_data")
    var responseData: String,
    @SerializedName("updated_at")
    var updatedAt: String,
    @SerializedName("user_id")
    var userId: String
)

data class OurPlan(
    @SerializedName("content")
    var content: String,
    @SerializedName("created_at")
    var createdAt: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("plan_history")
    var planHistory: String,
    @SerializedName("price")
    var price: String,
    @SerializedName("updated_at")
    var updatedAt: String,
    @SerializedName("plan_expired_date")
    var plan_expired_date:String
)


