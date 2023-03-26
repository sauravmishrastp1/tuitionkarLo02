package com.data.responseModel
import com.google.gson.annotations.SerializedName


data class RegisterResponse(
    @SerializedName("msg")
    var msg: String,
    @SerializedName("status")
    var status: Int,
    @SerializedName("user")
    var user: User
)

data class User(
    @SerializedName("aadhar")
    var aadhar: String,
    @SerializedName("amount")
    var amount: Any,
    @SerializedName("city")
    var city: String,
    @SerializedName("corresponding_address")
    var correspondingAddress: String,
    @SerializedName("country")
    var country: String,
    @SerializedName("created_at")
    var createdAt: String,
    @SerializedName("current_salary")
    var currentSalary: String,
    @SerializedName("deleted_at")
    var deletedAt: Any,
    @SerializedName("dob")
    var dob: String,
    @SerializedName("email_id")
    var emailId: String,
    @SerializedName("full_address")
    var fullAddress: String,
    @SerializedName("full_name")
    var fullName: String,
    @SerializedName("highest_qualification")
    var highestQualification: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("industry_name")
    var industryName: String,
    @SerializedName("is_approved")
    var isApproved: String,
    @SerializedName("is_deleted")
    var isDeleted: String,
    @SerializedName("is_expired")
    var isExpired: String,
    @SerializedName("job_specification")
    var jobSpecification: String,
    @SerializedName("job_title")
    var jobTitle: String,
    @SerializedName("listing_type")
    var listingType: Any,
    @SerializedName("mark_sheet")
    var markSheet: Any,
    @SerializedName("mobile_no")
    var mobileNo: String,
    @SerializedName("order_id")
    var orderId: Any,
    @SerializedName("package_id")
    var packageId: Any,
    @SerializedName("password")
    var password: String,
    @SerializedName("payment_id")
    var paymentId: Any,
    @SerializedName("payment_request_id")
    var paymentRequestId: Any,
    @SerializedName("payment_status")
    var paymentStatus: Any,
    @SerializedName("plan_expired_at")
    var planExpiredAt: Any,
    @SerializedName("profile_image")
    var profileImage: String,
    @SerializedName("profile_status")
    var profileStatus: String,
    @SerializedName("remark")
    var remark: Any,
    @SerializedName("resume")
    var resume: String,
    @SerializedName("state")
    var state: String,
    @SerializedName("total_experience")
    var totalExperience: String,
    @SerializedName("updated_at")
    var updatedAt: String,
    @SerializedName("user_id")
    var userId: String,
    @SerializedName("whatsapp_no")
    var whatsappNo: String
)