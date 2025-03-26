package com.data.remote.baseApi

import com.data.responseModel.*
import kotlinx.coroutines.Deferred
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.*

interface Api {
    @FormUrlEncoded
    @POST("job-seeker-login")
    fun loginApi(
        @Field("email") email: String,
        @Field("password") password: String
    ): Deferred<retrofit2.Response<LoginResponse>>


    @Multipart
    @POST("job-seeker-register")
    fun register(
        @Part("email_id") email_id: RequestBody?,
        @Part("full_name") full_name: RequestBody?,
        @Part("mobile_no") phone: RequestBody?,
        @Part("whatsapp_no") whatsapp_no: RequestBody?,
        @Part("dob") dob: RequestBody?,
        @Part("industry_name") industry_name: RequestBody?,
        @Part("job_title") job_title: RequestBody?,
        @Part("total_experience") total_experience: RequestBody?,
        @Part("highest_qualification") highest_qualification: RequestBody?,
        @Part("current_salary") current_salary: RequestBody?,
        @Part("city") city: RequestBody?,
        @Part("city_name") cityName: RequestBody?,
        @Part("state") state: RequestBody?,
        @Part("country") country: RequestBody?,
        @Part("full_address") email: RequestBody?,
        @Part resume: MultipartBody.Part?,
        @Part profile_image: MultipartBody.Part?,
        @Part aadhar: MultipartBody.Part?
    ): Deferred<retrofit2.Response<ResponseBody>>

    @Multipart
    @POST("update-profile")
    fun userUpdateProfile(
        @Part("id") id:RequestBody?,
        @Part("email_id") email_id: RequestBody?,
        @Part("full_name") full_name: RequestBody?,
        @Part("mobile_no") phone: RequestBody?,
        @Part("whatsapp_no") whatsapp_no: RequestBody?,
        @Part("dob") dob: RequestBody?,
        @Part("industry_name") industry_name: RequestBody?,
        @Part("job_title") job_title: RequestBody?,
        @Part("total_experience") total_experience: RequestBody?,
        @Part("highest_qualification") highest_qualification: RequestBody?,
        @Part("current_salary") current_salary: RequestBody?,
        @Part("city") city: RequestBody?,
        @Part("city_name") cityName: RequestBody?,
        @Part("state") state: RequestBody?,
        @Part("country") country: RequestBody?,
        @Part("full_address") email: RequestBody?,
        @Part resume: MultipartBody.Part?,
        @Part profile_image: MultipartBody.Part?,
        @Part aadhar: MultipartBody.Part?
    ): Deferred<retrofit2.Response<ResponseBody>>

    @POST("job-list")
    fun getJobList(@Body parameterName: HashMap<String, Any>): Deferred<retrofit2.Response<JobListResponse>>

    @FormUrlEncoded
    @POST("candidate-dashboard")
    fun getcandidateDashbaord(@Field("user_id") user_id: Int): Deferred<retrofit2.Response<CandidateDashbaordResponseModel>>

    @FormUrlEncoded
    @POST("job-detail")
    fun getJobDetails(
        @Field("id") id: Int,
        @Field("user_id") user_id: Int
    ): Deferred<retrofit2.Response<JobDetailsResponse>>

    @FormUrlEncoded
    @POST("candidate-job-applied")
    fun getAppliedJobs(@Field("user_id") userId: Int): Deferred<retrofit2.Response<AppliedJobsResponse>>

    @FormUrlEncoded
    @POST(" job-seeker-profile")
    fun jobSeekerProfile(@Field("user_id") userId: Int): Deferred<retrofit2.Response<LoginResponse>>

    @POST("all-country")
    fun allCountry(): Deferred<retrofit2.Response<AllCountryResponse>>

    @FormUrlEncoded
    @POST("all-state")
    fun getAllState(@Field("country_id") country_id: Int): Deferred<retrofit2.Response<StateResponse>>

    @FormUrlEncoded
    @POST("all-city")
    fun getAllCity(@Field("state_id") state_id: Int): Deferred<retrofit2.Response<CityResponse>>

    @POST("all-city")
    fun getAllCity(): Deferred<retrofit2.Response<CityResponse>>

    @FormUrlEncoded
    @POST("job-seeker-subscription")
    fun getSubscription(@Field("user_id") user_id: Int): Deferred<retrofit2.Response<SubscriptionResponse>>

    @FormUrlEncoded
    @POST("generate-order-id")
    fun generateOrderId(
        @Field("user_id") user_id: Int,
        @Field("id") plan_id: Int
    ): Deferred<retrofit2.Response<OrderIdResponse>>

    @FormUrlEncoded
    @POST("store-payment-detail")
    fun storePaymnetDetails(
        @Field("user_id") userId: Int,
        @Field("razorpay_payment_id") razorpay_payment_id: String,
        @Field("order_id")plan_id:String
    ): Deferred<retrofit2.Response<ResponseBody>>

    @FormUrlEncoded
    @POST("job-apply")
    fun jobApply(
        @Field("user_id") user_id: Int,
        @Field("job_id") job_id: Int
    ): Deferred<retrofit2.Response<ApplideJobSucessResponse>>

    @FormUrlEncoded
    @POST("get-remark")
    fun getRemark(@Field("user_id") user_id: Int): Deferred<retrofit2.Response<RemarkListResponse>>

    @FormUrlEncoded
    @POST("job-provider-register")
    fun jobProviderRegisterForm(
        @Field("job_category") job_category: Int,
        @Field("designation") designation: String,
        @Field("institute_name") institute_name: String,
        @Field("requirement") requirement: String,
        @Field("qualification_needed") qualification_needed: String,
        @Field("experience_needed")experience_needed:String,
        @Field("salary")salary:String,
        @Field("process_of_selections")process_of_selections:String,
        @Field("job_location")job_location:Int,
        @Field("company_phone")company_phone:String,
        @Field("description")description:String,
        @Field("company_name")company_name:String

        ):Deferred<retrofit2.Response<ResponseBody>>


    @FormUrlEncoded
    @POST("career-at-tkl-register")
    fun careerAtTklRegisterForm(
        @Field("full_name") full_name: String,
        @Field("email") email: String,
        @Field("phone") phone: String,
        @Field("address") address: String,
        @Field("dob") dob: String,
        @Field("gender")gender:String,
    ):Deferred<retrofit2.Response<ResponseBody>>


    @FormUrlEncoded
    @POST("forgot-password")
    fun forgetPassword(@Field("email")email:String):Deferred<retrofit2.Response<ResponseBody>>

}