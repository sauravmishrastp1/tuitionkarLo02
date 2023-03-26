package com.tklpvtltd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.tklpvtltd.databinding.ActivityLoginBinding
import com.tklpvtltd.ui.ForgetPasswordActivity
import com.tklpvtltd.utils.prefrence.SessionManager

class LoginActivity : AppCompatActivity() {
     private lateinit var binding: ActivityLoginBinding
     private val authViewModel:AuthViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.signIn.setOnClickListener {
            authViewModel.login(binding.email.text.toString(),binding.password.text.toString())
        }
         observer()
        binding.register.setOnClickListener {
            startActivity(Intent(applicationContext,RegisterActivity::class.java))
            finish()
        }
        binding.forgotPassword.setOnClickListener {
            startActivity(Intent(applicationContext,ForgetPasswordActivity::class.java))
            finish()
        }
    }
    fun observer(){
        authViewModel.loginResponse.observe(this){
            if(it.body()!!.status==200){
                SessionManager.getInstance(this).setIsLogin(true)
                SessionManager.getInstance(this).setUserId(it.body()!!.user.userId.toInt())
                SessionManager.getInstance(this).setUserName(it.body()!!.user.fullName)
                SessionManager.getInstance(this).setUserEmail(it.body()!!.user.emailId)
                SessionManager.getInstance(this).setPhone(it.body()!!.user.mobileNo)
                //   SessionManager.getInstance(this).setIsLogin(true)
                startActivity(Intent(applicationContext,MainActivity::class.java))
                finish()
            }else if(it.body()!!.status==202){
                showAlert(it.body()!!.msg,"Error")

            }else if(it.body()!!.status ==400){
                showAlert(it.body()!!.msg,"Error")

            }

        }
    }
    fun showAlert(message: String,title:String) {
        val alertDialog = AlertDialog.Builder(this).create()
        alertDialog.setTitle(title)
        alertDialog.setMessage(message)
        alertDialog.setButton(
            AlertDialog.BUTTON_POSITIVE, "OK"
        ) { dialog, which -> dialog.dismiss() }
        alertDialog.show()

    }




}