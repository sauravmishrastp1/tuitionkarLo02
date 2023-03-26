package com.data.remote.baseApi

import com.data.responseModel.LoginResponse

interface BaseInterface {
    fun getUpdateProfileDetails(response:retrofit2.Response<LoginResponse>)
}