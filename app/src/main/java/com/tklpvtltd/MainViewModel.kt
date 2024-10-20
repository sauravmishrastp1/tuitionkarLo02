package com.tklpvtltd

import android.print.PrintJobId
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.data.remote.baseApi.BaseInterface
import com.data.remote.baseApi.Repository
import com.data.responseModel.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainViewModel : ViewModel(), KoinComponent {
    private val repository: Repository by inject()
    var response: MutableLiveData<retrofit2.Response<JobDetailsResponse>> = MutableLiveData()
    var appliedJobsResponse: MutableLiveData<retrofit2.Response<AppliedJobsResponse>> =
        MutableLiveData()
    var profileResponse: MutableLiveData<retrofit2.Response<LoginResponse>> = MutableLiveData()
    var subscriptionResponse: MutableLiveData<retrofit2.Response<SubscriptionResponse>> =
        MutableLiveData()
    var orderIdResponse: MutableLiveData<retrofit2.Response<OrderIdResponse>> = MutableLiveData()
    var storePaymentDetailsResponse: MutableLiveData<retrofit2.Response<ResponseBody>> =
        MutableLiveData()
    var jobApplyResponse: MutableLiveData<retrofit2.Response<ApplideJobSucessResponse>> =
        MutableLiveData()
    var candidateDashbaordResponse: MutableLiveData<retrofit2.Response<CandidateDashbaordResponseModel>> =
        MutableLiveData()
    var remarkListResponse: MutableLiveData<retrofit2.Response<RemarkListResponse>> =
        MutableLiveData()
    var jobProviderResponse: MutableLiveData<retrofit2.Response<ResponseBody>> = MutableLiveData()
    var carrerAtTklResponse: MutableLiveData<retrofit2.Response<ResponseBody>> = MutableLiveData()
    var forgetPasswordResponse :MutableLiveData<retrofit2.Response<ResponseBody>> = MutableLiveData()
    var baseInterface:BaseInterface?=null

    fun getJobDetails(id: Int, userId: Int) {
        viewModelScope.launch {
            repository.getJobDetails(id, userId)
                .onStart {

                }
                .catch {

                }

                .collect {
                    response.value = it
                }

        }
    }

    fun getAppliedJobs(userId: Int) {
        viewModelScope.launch {
            repository.getAppliedJobs(userId)
                .onStart {

                }
                .catch {

                }

                .collect {
                    appliedJobsResponse.value = it
                }
        }
    }

    fun getJobSeejerProfile(userId: Int) {
        viewModelScope.launch {
            repository.getJobSeekerProfile(userId)
                .onStart {

                }
                .catch {

                }

                .collect {
                    baseInterface?.getUpdateProfileDetails(it)
                }
        }
    }

    fun getUserSuscribtionPlan(userId: Int) {
        viewModelScope.launch {
            repository.getSuscribtion(userId)

                .onStart {

                }
                .catch {
                    Log.e("erroe", it.message.toString())
                }

                .collect {
                    subscriptionResponse.value = it
                }
        }
    }

    fun generateOrderId(userId: Int, planId: Int) {
        viewModelScope.launch {
            repository.getOrderId(userId, planId)
                .onStart {

                }
                .catch {

                }

                .collect {
                    orderIdResponse.value = it
                }
        }
    }

    fun storePaymentDeatils(userId: Int, razorPayId: String,planId: String) {
        viewModelScope.launch {
            repository.storePaymnetDetails(userId, razorPayId,planId)
                .onStart {

                }
                .catch {

                }

                .collect {
                    storePaymentDetailsResponse.value = it
                }
        }
    }

    fun appJob(userId: Int, jobId: Int) {
        viewModelScope.launch {
            repository.jobApply(userId, jobId)
                .onStart {

                }
                .catch {

                }

                .collect {
                    jobApplyResponse.value = it
                }
        }
    }

    fun getCandidateDashboard(userId: Int) {
        viewModelScope.launch {
            repository.getcandidateDashbaord(userId)
                .onStart {

                }
                .catch {

                }

                .collect {
                    candidateDashbaordResponse.value = it
                }
        }
    }

    fun getRemarkList(userId: Int) {
        viewModelScope.launch {
            repository.getRemark(userId)
                .onStart {

                }
                .catch {

                }

                .collect {
                    remarkListResponse.value = it
                }
        }
    }

    fun jobProviderForm(
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
    ) {
        viewModelScope.launch {
            repository.jobProviderRegisterForm(
                job_category,
                designation,
                institute_name,
                requirement,
                qualification_needed,
                experience_needed,
                salary,
                process_of_selections,
                job_location,
                company_phone,
                description,
                company_name
            )
                .onStart {

                }
                .catch {

                }

                .collect {
                    jobProviderResponse.value = it
                }
        }
    }

    fun carrerAtTklForm(
        full_name: String,
        email: String,
        phone: String,
        address: String,
        dob: String,
        gender: String,
    ) {
        viewModelScope.launch {
            repository.careerAtTklRegisterForm(full_name, email, phone, address, dob, gender)
                .onStart {

                }
                .catch {

                }

                .collect {
                    carrerAtTklResponse.value = it
                }
        }
        }

    fun forgetPassword(email: String){
        viewModelScope.launch {
            repository.forgetPassword(email)
                .onStart {

                }
                .catch {

                }

                .collect {
                    forgetPasswordResponse.value = it
                }

        }
    }


    }