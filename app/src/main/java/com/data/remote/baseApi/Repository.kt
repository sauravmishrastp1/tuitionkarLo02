package com.data.remote.baseApi

import com.data.responseModel.*
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Field
import java.io.File

interface Repository {
    suspend fun login(userName: String, password: String): Flow<retrofit2.Response<LoginResponse>>
    suspend fun Register(
        registerModelParameter: RegisterModelParameter,
        resume: File,
        addharCard: File,
        profileImage: File
    ): Flow<retrofit2.Response<ResponseBody>>


    suspend fun UpdateProfile(
        registerModelParameter: RegisterModelParameter,
        resume: File,
        addharCard: File,
        profileImage: File
    ): Flow<retrofit2.Response<ResponseBody>>

    suspend fun getJobList(
        post_date: Int,
        keyword: String,
        location: String,
        userId: Int
    ): Flow<retrofit2.Response<JobListResponse>>

    suspend fun getJobDetails(id: Int, userId: Int): Flow<retrofit2.Response<JobDetailsResponse>>
    suspend fun getAppliedJobs(userId: Int): Flow<retrofit2.Response<AppliedJobsResponse>>
    suspend fun getJobSeekerProfile(userId: Int): Flow<retrofit2.Response<LoginResponse>>
    suspend fun getCountry(): Flow<retrofit2.Response<AllCountryResponse>>
    suspend fun getState(country_id: Int): Flow<retrofit2.Response<StateResponse>>
    suspend fun getCity(sate_id: Int): Flow<Response<CityResponse>>
    suspend fun getCity(): Flow<Response<CityResponse>>
    suspend fun getSuscribtion(userId: Int): Flow<Response<SubscriptionResponse>>
    suspend fun getOrderId(userId: Int, planId: Int): Flow<Response<OrderIdResponse>>
    suspend fun storePaymnetDetails(
        userId: Int,
        razorpay_payment_id: String,
        plan_id:String
    ): Flow<Response<ResponseBody>>

    suspend fun jobApply(userId: Int, jobId: Int): Flow<Response<ApplideJobSucessResponse>>
    suspend fun getcandidateDashbaord(user_id: Int): Flow<Response<CandidateDashbaordResponseModel>>
    suspend fun getRemark(user_id: Int): Flow<Response<RemarkListResponse>>
    fun jobProviderRegisterForm(
        job_category: String,
        designation: String,
        institute_name: String,
        requirement: String,
        qualification_needed: String,
        experience_needed: String,
        salary: String,
        process_of_selections: String,
        job_location: String,
        company_phone: String,
        description: String,
        company_name:String
    ): Flow<retrofit2.Response<ResponseBody>>

    fun careerAtTklRegisterForm(
        full_name: String,
        email: String,
        phone: String,
        address: String,
        dob: String,
        gender: String,
    ): Flow<retrofit2.Response<ResponseBody>>

    fun forgetPassword(email:String):Flow<retrofit2.Response<ResponseBody>>


}