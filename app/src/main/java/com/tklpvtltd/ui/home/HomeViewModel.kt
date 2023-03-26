package com.tklpvtltd.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.data.responseModel.JobListResponse
import com.data.remote.baseApi.Repository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@KoinApiExtension
class HomeViewModel : ViewModel(), KoinComponent {
    private val repository: Repository by inject()
    var jobListResponse :MutableLiveData<retrofit2.Response<JobListResponse>> = MutableLiveData()
    fun getJobLIst(postDate:Int,keyWord:String,location:String,userId:Int){
        viewModelScope.launch {
            repository.getJobList(postDate,keyWord,location,userId)
                .onStart {

                }
                .catch {

                }
                .onCompletion {

                }
                .collect {

                    jobListResponse.value = it


                }

        }

    }


}