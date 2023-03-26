package com.tklpvtltd.utils.prefrence

import android.content.Context
import android.content.SharedPreferences

open class SessionManager protected constructor(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("wayja", Context.MODE_PRIVATE)

    companion object {
        private var preferenceManager: SessionManager? = null
        private var IS_LOGIN = "IS_LOGIN"
        private var USER_ID = "USER_ID"
        private var USER_NAME = "USER_NAME"
        private var MOBILE = "MOBILE"
        private var EMAIL = "EMAIL"

        fun getInstance(context: Context): SessionManager {
            if (preferenceManager == null)
                preferenceManager =
                    SessionManager(context)
            return preferenceManager as SessionManager
        }
    }



    fun setIsLogin(isLogout: Boolean) {
        sharedPreferences.edit().putBoolean(IS_LOGIN, isLogout).apply()
    }

    val isLogin: Boolean
        get() = sharedPreferences.getBoolean(IS_LOGIN, false)

    fun setUserName(userName: String) {
        sharedPreferences.edit().putString(USER_NAME, userName).apply()
    }


    val getUserName: String
        get() = sharedPreferences.getString(USER_NAME, "")!!


    fun setUserId(userId: Int) {
        sharedPreferences.edit().putInt(USER_ID, userId).apply()
    }


    val getUserId: Int
        get() = sharedPreferences.getInt(USER_ID, 0)


    fun setUserEmail(userEmail: String) {
        sharedPreferences.edit().putString(EMAIL, userEmail).apply()
    }

    val getUserEmail: String
        get() = sharedPreferences.getString(EMAIL, "")!!


    fun setPhone(phone: String) {
        sharedPreferences.edit().putString(MOBILE, phone).apply()

    }

    val getPhone: String
        get() = sharedPreferences.getString(MOBILE, "")!!





    open fun clear() {
        sharedPreferences.edit().clear().apply()
    }
}