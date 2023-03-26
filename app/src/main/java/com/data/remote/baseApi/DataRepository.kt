package com.data.remote.baseApi

import com.data.responseModel.*
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.ResponseBody
import retrofit2.Response
import java.io.File

class DataRepository constructor(private var api: Api) : Repository {
    companion object {
        private const val URL_GET_PUBLIC_IP = "https://ifconfig.me/all.json"
        private const val NOTHING_GET = "Nothing get!"
        private const val URL_LOGIN = "http://private-222d3-homework5.apiary-mock.com/api/login"
        const val BASE_URL = "https://tklpvtltd.com/tkl_new/api/"
        const val IMAGE_BASE_URL = "https://tklpvtltd.com/"


    }


    override suspend fun login(userName: String, password: String) = flow {

        val response = api.loginApi(userName, password).await()

        emit(response)
    }.flowOn(Dispatchers.IO)


    override suspend fun Register(
        registerModelParameter: RegisterModelParameter,
        resume: File,
        addharCard: File,
        profileImage: File
    ): Flow<Response<ResponseBody>> = flow {

        val response = api.register(
            (MultiPartHelperClass.getRequestBody(registerModelParameter.email_id)),
            (MultiPartHelperClass.getRequestBody(registerModelParameter.full_name)),
            (MultiPartHelperClass.getRequestBody(registerModelParameter.mobile_no)),
            (MultiPartHelperClass.getRequestBody(registerModelParameter.whatsapp_no)),
            (MultiPartHelperClass.getRequestBody(registerModelParameter.dob)),
            (MultiPartHelperClass.getRequestBody(registerModelParameter.industry_name)),
            (MultiPartHelperClass.getRequestBody(registerModelParameter.job_title)),
            (MultiPartHelperClass.getRequestBody(registerModelParameter.total_experience.toString())),
            (MultiPartHelperClass.getRequestBody(registerModelParameter.highest_qualification)),
            (MultiPartHelperClass.getRequestBody(registerModelParameter.current_salary)),
            (MultiPartHelperClass.getRequestBody(registerModelParameter.city.toString())),
            (MultiPartHelperClass.getRequestBody(registerModelParameter.cityName)),
            (MultiPartHelperClass.getRequestBody(registerModelParameter.state.toString())),
            (MultiPartHelperClass.getRequestBody(registerModelParameter.country.toString())),
            (MultiPartHelperClass.getRequestBody(registerModelParameter.fullAddress)),
            MultiPartHelperClass.getMultipartData(resume, "resume"),
            MultiPartHelperClass.getMultipartData(profileImage, "profile_image"),
            MultiPartHelperClass.getMultipartData(addharCard, "aadhar")
        ).await()
        emit(response)
    }.flowOn(Dispatchers.IO)

    override suspend fun UpdateProfile(
        registerModelParameter: RegisterModelParameter,
        resume: File,
        addharCard: File,
        profileImage: File
    ): Flow<Response<ResponseBody>> = flow {
        val response = api.userUpdateProfile(
            (MultiPartHelperClass.getRequestBody(registerModelParameter.id)),
            (MultiPartHelperClass.getRequestBody(registerModelParameter.email_id)),
            (MultiPartHelperClass.getRequestBody(registerModelParameter.full_name)),
            (MultiPartHelperClass.getRequestBody(registerModelParameter.mobile_no)),
            (MultiPartHelperClass.getRequestBody(registerModelParameter.whatsapp_no)),
            (MultiPartHelperClass.getRequestBody(registerModelParameter.dob)),
            (MultiPartHelperClass.getRequestBody(registerModelParameter.industry_name)),
            (MultiPartHelperClass.getRequestBody(registerModelParameter.job_title)),
            (MultiPartHelperClass.getRequestBody(registerModelParameter.total_experience.toString())),
            (MultiPartHelperClass.getRequestBody(registerModelParameter.highest_qualification)),
            (MultiPartHelperClass.getRequestBody(registerModelParameter.current_salary)),
            (MultiPartHelperClass.getRequestBody(registerModelParameter.city.toString())),
            (MultiPartHelperClass.getRequestBody(registerModelParameter.cityName)),
            (MultiPartHelperClass.getRequestBody(registerModelParameter.state.toString())),
            (MultiPartHelperClass.getRequestBody(registerModelParameter.country.toString())),
            (MultiPartHelperClass.getRequestBody(registerModelParameter.fullAddress)),
            MultiPartHelperClass.getMultipartData(resume, "resume"),
            MultiPartHelperClass.getMultipartData(profileImage, "profile_image"),
            MultiPartHelperClass.getMultipartData(addharCard, "aadhar")).await()
            emit(response)

    }.flowOn(Dispatchers.IO)

    override suspend fun getJobList(
        post_date: Int,
        keyword: String,
        location: String,
        userId: Int
    ): Flow<Response<JobListResponse>> = flow {
        if (post_date == 0) {
            val parameter: HashMap<String, Any> = hashMapOf()
            parameter["post_date"] = "all"
            parameter["keyword"] = keyword
            parameter["location"] = location
            parameter["user_id"] = userId
            val response = api.getJobList(parameter).await()
            emit(response)
        } else {
            val parameter: HashMap<String, Any> = hashMapOf()
            parameter["post_date"] = post_date.toString()
            parameter["keyword"] = keyword
            parameter["location"] = location
            parameter["user_id"] = userId
            val response = api.getJobList(parameter).await()
            emit(response)

        }

    }.flowOn(Dispatchers.IO)

    override suspend fun getJobDetails(id: Int, userId: Int): Flow<Response<JobDetailsResponse>> =
        flow {

            val response = api.getJobDetails(id, userId).await()
            emit(response)

        }.flowOn(Dispatchers.IO)

    override suspend fun getAppliedJobs(userId: Int): Flow<Response<AppliedJobsResponse>> = flow {
        val response = api.getAppliedJobs(userId).await()
        emit(response)

    }.flowOn(Dispatchers.IO)

    override suspend fun getJobSeekerProfile(userId: Int): Flow<Response<LoginResponse>> = flow {
        val response = api.jobSeekerProfile(userId).await()
        emit(response)

    }.flowOn(Dispatchers.IO)

    override suspend fun getCountry(): Flow<Response<AllCountryResponse>> = flow {
        val response = api.allCountry().await()
        emit(response)
    }.flowOn(Dispatchers.IO)

    override suspend fun getState(country_id: Int): Flow<Response<StateResponse>> = flow {
        val response = api.getAllState(country_id).await()
        emit(response)
    }.flowOn(Dispatchers.IO)

    override suspend fun getCity(sate_id: Int): Flow<Response<CityResponse>> = flow {
        val response = api.getAllCity(sate_id).await()
        emit(response)
    }.flowOn(Dispatchers.IO)


    override suspend fun getCity(): Flow<Response<CityResponse>> = flow {
        val response = api.getAllCity().await()
        emit(response)
    }.flowOn(Dispatchers.IO)



    override suspend fun getSuscribtion(userId: Int): Flow<Response<SubscriptionResponse>> = flow {
        val response = api.getSubscription(userId).await()
        emit(response)
    }.flowOn(Dispatchers.IO)

    override suspend fun getOrderId(userId: Int, planId: Int): Flow<Response<OrderIdResponse>> =
        flow {
            val response = api.generateOrderId(userId, planId).await()
            emit(response)
        }.flowOn(Dispatchers.IO)

    override suspend fun storePaymnetDetails(
        userId: Int,
        razorpay_payment_id: String,
        plan_id: String
    ): Flow<Response<ResponseBody>> = flow {
        val response = api.storePaymnetDetails(userId, razorpay_payment_id,plan_id).await()
        emit(response)
    }.flowOn(Dispatchers.IO)

    override suspend fun jobApply(
        userId: Int,
        jobId: Int
    ): Flow<Response<ApplideJobSucessResponse>> = flow {
        val response = api.jobApply(userId, jobId).await()
        emit(response)
    }.flowOn(Dispatchers.IO)

    override suspend fun getcandidateDashbaord(user_id: Int): Flow<Response<CandidateDashbaordResponseModel>> =
        flow {
            val response = api.getcandidateDashbaord(user_id).await()
            emit(response)
        }.flowOn(Dispatchers.IO)

    override suspend fun getRemark(user_id: Int): Flow<Response<RemarkListResponse>> = flow {
        val response = api.getRemark(user_id).await()
        emit(response)
    }.flowOn(Dispatchers.IO)

    override fun jobProviderRegisterForm(
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
    ): Flow<Response<ResponseBody>> = flow<Response<ResponseBody>> {
        val response = api.jobProviderRegisterForm(
            job_category.toInt(),
            designation,
            institute_name,
            requirement,
            qualification_needed,
            experience_needed,
            salary,
            process_of_selections,
            job_location.toInt(),
            company_phone,
            description,
            company_name
        ).await()
        emit(response)
    }.flowOn(Dispatchers.IO)

    override fun careerAtTklRegisterForm(
        full_name: String,
        email: String,
        phone: String,
        address: String,
        dob: String,
        gender: String
    ): Flow<Response<ResponseBody>> = flow<Response<ResponseBody>> {
        val response =
            api.careerAtTklRegisterForm(full_name, email, phone, address, dob, gender).await()
        emit(response)

    }.flowOn(Dispatchers.IO)

    override fun forgetPassword(email: String): Flow<Response<ResponseBody>> = flow{
        val response =
            api.forgetPassword(email).await()
        emit(response)
    }.flowOn(Dispatchers.IO)


}