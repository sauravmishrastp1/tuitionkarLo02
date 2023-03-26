package com.data.responseModel
import com.google.gson.annotations.SerializedName



 data class CandidateDashbaordResponseModel(
    @SerializedName("data")
    val `data`: DashboardData,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("tile")
    val tile: Tile
)

data class DashboardData(
    @SerializedName("active_job_apply")
    val activeJobApply: List<ActiveJobApply>,
    @SerializedName("job_apply")
    val jobApply: List<JobApply>,
    @SerializedName("profile")
    val profile: Profile,
    @SerializedName("remark")
    val remark: List<Any>
)

data class Tile(
    @SerializedName("notifications")
    val notifications: Int,
    @SerializedName("total_active_job")
    val totalActiveJob: Int,
    @SerializedName("total_job_applied")
    val totalJobApplied: Int
)

data class ActiveJobApply(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("expired_at")
    val expiredAt: Any,
    @SerializedName("id")
    val id: Int,
    @SerializedName("is_expired")
    val isExpired: String,
    @SerializedName("job_id")
    val jobId: String,
    @SerializedName("job_seeker_id")
    val jobSeekerId: String,
    @SerializedName("job_seeker_user_id")
    val jobSeekerUserId: String,
    @SerializedName("updated_at")
    val updatedAt: String
)

data class JobApply(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("expired_at")
    val expiredAt: Any,
    @SerializedName("id")
    val id: Int,
    @SerializedName("is_expired")
    val isExpired: String,
    @SerializedName("job_id")
    val jobId: String,
    @SerializedName("job_seeker_id")
    val jobSeekerId: String,
    @SerializedName("job_seeker_user_id")
    val jobSeekerUserId: String,
    @SerializedName("updated_at")
    val updatedAt: String
)

