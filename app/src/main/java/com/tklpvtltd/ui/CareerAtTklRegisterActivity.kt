package com.tklpvtltd.ui

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.tklpvtltd.MainViewModel
import com.tklpvtltd.R
import com.tklpvtltd.databinding.ActivityCareerAtTklBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class CareerAtTklRegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCareerAtTklBinding
    private val mainViewModel by viewModel<MainViewModel>()
    private val myCalendar = Calendar.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCareerAtTklBinding.inflate(layoutInflater)
        binding.actionBar.toolbarHeaderText.text = "Back"
        binding.actionBar.sideMenu.setOnClickListener {
            onBackPressed()
        }
        setContentView(binding.root)
        binding.jobProviderApply.setOnClickListener {
            submitData(binding.nameJobProvider.text.toString(),binding.emailJobProvider.text.toString(),binding.phnJobProvider.text.toString(),binding.comAddJobProvider.text.toString(),binding.jobDescJobProvider.text.toString(),"")
        }
        binding.jobDescJobProvider.setOnClickListener {
            showDatePicker()
        }
        mainViewModel.carrerAtTklResponse.observe(this){
            showAlert("Application sent successfully","")
        }

    }
    fun submitData( full_name: String,
                    email: String,
                    phone: String,
                    address: String,
                    dob: String,
                    gender: String,){
        mainViewModel.carrerAtTklForm(full_name, email, phone, address, dob, "Male")
    }

    fun showDatePicker() {
        val date = DatePickerDialog.OnDateSetListener { view, year, month, day ->
            binding.jobDescJobProvider.setText("${year}-${month + 1}-${day}")

        }
        val datePickerDialog = DatePickerDialog(
            this,
            date,
            myCalendar.get(Calendar.YEAR),
            myCalendar.get(Calendar.MONTH),
            myCalendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()


    }

    private fun showAlert(message: String, title: String) {
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