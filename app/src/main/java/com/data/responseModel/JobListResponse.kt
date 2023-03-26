package com.data.responseModel
import com.google.gson.annotations.SerializedName


data class JobListResponse(
    @SerializedName("job_count")
    var jobCount: Int,
    @SerializedName("job_list")
    var jobList: List<JobList>,
    @SerializedName("msg")
    var msg: String,
    @SerializedName("status")
    var status: Int
)

data class JobList(
    @SerializedName("city_name")
    var cityName: String,
    @SerializedName("company_name")
    var companyName: String,
    @SerializedName("company_phone")
    var companyPhone: String,
    @SerializedName("created_at")
    var createdAt: String,
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
    var isAppliedJob:Boolean=false
)