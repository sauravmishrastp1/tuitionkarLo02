package com.tklpvtltd.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.tklpvtltd.MainViewModel
import com.tklpvtltd.R
import com.tklpvtltd.databinding.ActivityForgetPasswordBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ForgetPasswordActivity : AppCompatActivity() {
    private val mainViewModel by viewModel<MainViewModel>()

    private lateinit var binding :ActivityForgetPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgetPasswordBinding.inflate(layoutInflater)
        binding.actionBar.toolbarHeaderText.text = "Reset Password"

        setContentView(binding.root)
            binding.signIn.setOnClickListener {
                mainViewModel.forgetPassword(binding.email.text.toString())

            }

        mainViewModel.forgetPasswordResponse.observe(this){
            showAlert("Link sent your give link","Success")
        }
    }

    private fun showAlert(message: String, title:String) {
        val alertDialog = AlertDialog.Builder(this).create()
        alertDialog.setTitle(title)
        alertDialog.setCancelable(false)
        alertDialog.setMessage(message)
        alertDialog.setButton(
            AlertDialog.BUTTON_POSITIVE, "OK"
        ) { dialog, which ->
            dialog.dismiss()

        }
        alertDialog.show()

    }


}