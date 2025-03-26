package com.tklpvtltd

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.data.remote.baseApi.Repository
import com.data.responseModel.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.io.File

class AuthViewModel :ViewModel(), KoinComponent {
   private val repository: Repository by inject()
    var loginResponse :MutableLiveData<retrofit2.Response<LoginResponse>> = MutableLiveData()
    var registerResponse :MutableLiveData<retrofit2.Response<ResponseBody>> = MutableLiveData()
    var allCountryResponse:MutableLiveData<retrofit2.Response<AllCountryResponse>> = MutableLiveData()
    var stateResponse:MutableLiveData<retrofit2.Response<StateResponse>> = MutableLiveData()
    var cityResponse:MutableLiveData<retrofit2.Response<CityResponse>> = MutableLiveData()
    var updateProfile:UpdateProfile?=null
    var registerModelParameter:RegisterModelParameter?=null
    var resume:File?=null
    var updateResume:File?=null
    var aadharCard:File?=null
    var updateAadharCard:File?=null
    var profileImage:File?=null
    var updateProfileImage:File?=null
    var getLocation:GetLocation?=null
    fun login(userName:String,password:String) {
        viewModelScope.launch {
           repository.login(userName,password)
                .onStart {

                }.catch {

                }
                .onCompletion {

                }.collect {

                   loginResponse.value = it


                }



        }
    }
    fun register(){

        viewModelScope.launch {
            repository.Register(registerModelParameter!!,resume!!,aadharCard!!, profileImage!!)
                .onStart {

                }.catch {
                    Log.e("Error",it!!.message.toString())

                }
                .onCompletion {

                }.collect {

                    registerResponse.value = it

                }
        }
    }

    fun updatePrfile(){

        viewModelScope.launch {
            repository.UpdateProfile(registerModelParameter!!,resume!!,aadharCard!!, profileImage!!)
                .onStart {

                }.catch {
                    Log.e("Error",it!!.message.toString())

                }
                .onCompletion {

                }.collect {
                    updateProfile?.updateProfile()


                }
        }
    }

    fun getCountry(){
        viewModelScope.launch {
            repository.getCountry()
                .onStart {

                }.catch {

                }
                .onCompletion {

                }.collect {

                    allCountryResponse.value = it
                    getLocation?.getCountry(it)


                }

        }
    }


    fun getSate(countryId:Int){
        viewModelScope.launch {
            repository.getState(countryId)
                .onStart {

                }.catch {

                }
                .onCompletion {

                }.collect {

                    stateResponse.value = it
                    getLocation?.getstate(it)



                }

        }
    }

    fun getCity(state_id:Int){
        viewModelScope.launch {
            repository.getCity(state_id)
                .onStart {

                }.catch {

                }
                .onCompletion {

                }.collect {
                    getLocation?.getCity(it)

                    cityResponse.value = it


                }

        }
    }

    fun getCity(){
        viewModelScope.launch {
            repository.getCity()
                .onStart {

                }.catch {

                }
                .onCompletion {

                }.collect {
                    getLocation?.getCity(it)

                    cityResponse.value = it


                }

        }
    }




    fun getCountry1(){
        viewModelScope.launch {
            repository.getCountry()
                .onStart {

                }.catch {

                }
                .onCompletion {

                }.collect {
                     getLocation?.getCountry(it)
                   // allCountryResponse.value = it


                }

        }
    }


    fun getSate1(countryId:Int){
        viewModelScope.launch {
            repository.getState(countryId)
                .onStart {

                }.catch {

                }
                .onCompletion {

                }.collect {

                    stateResponse.value = it
                    getLocation?.getstate(it)



                }

        }
    }

    fun getCity1(state_id:Int){
        viewModelScope.launch {
            repository.getCity(state_id)
                .onStart {

                }.catch {

                }
                .onCompletion {

                }.collect {
                    getLocation?.getCity(it)



                }

        }
    }


}

interface  GetLocation{
    fun getstate(it :retrofit2.Response<StateResponse>)
    fun getCity(it:retrofit2.Response<CityResponse>)
    fun getCountry(it:retrofit2.Response<AllCountryResponse>)
}
interface UpdateProfile{
    fun updateProfile()
}
