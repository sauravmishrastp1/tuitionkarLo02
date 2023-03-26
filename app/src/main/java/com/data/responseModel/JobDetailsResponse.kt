package com.data.responseModel
import com.google.gson.annotations.SerializedName


data class JobDetailsResponse(
    @SerializedName("job_detail")
    var jobDetail: JobDetail,
    @SerializedName("msg")
    var msg: String,
    @SerializedName("status")
    var status: Int
)

data class JobDetail(
    @SerializedName("city_name")
    var cityName: String,
    @SerializedName("company_name")
    var companyName: String,
    @SerializedName("company_phone")
    var companyPhone: String,
    @SerializedName("created_at")
    var createdAt: String,
    @SerializedName("date")
    var date: String,
    @SerializedName("deleted_at")
    var deletedAt: Any,
    @SerializedName("description")
    var description: String,
    @SerializedName("designation")
    var designation: String,
    @SerializedName("experience_needed")
    var experienceNeeded: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("institute_name")
    var instituteName: String,
    @SerializedName("is_active")
    var isActive: String,
    @SerializedName("is_approved")
    var isApproved: String,
    @SerializedName("is_deleted")
    var isDeleted: String,
    @SerializedName("job_category")
    var jobCategory: String,
    @SerializedName("job_location")
    var jobLocation: String,
    @SerializedName("job_time")
    var jobTime: String,
    @SerializedName("logo")
    var logo: Any,
    @SerializedName("process_of_selections")
    var processOfSelections: String,
    @SerializedName("qualification_needed")
    var qualificationNeeded: String,
    @SerializedName("requirement")
    var requirement: String,
    @SerializedName("salary")
    var salary: String,
    @SerializedName("updated_at")
    var updatedAt: String,
    @SerializedName("is_apply")
    var is_apply:String
)