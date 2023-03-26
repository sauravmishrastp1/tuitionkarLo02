package com.tklpvtltd.ui

import android.content.Intent
import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.data.remote.baseApi.DataRepository.Companion.IMAGE_BASE_URL
import com.data.responseModel.JobList
import com.google.gson.Gson
import com.tklpvtltd.LoginActivity
import com.tklpvtltd.MainViewModel
import com.tklpvtltd.R
import com.tklpvtltd.databinding.ActivityJobDetailsBinding
import com.tklpvtltd.utils.prefrence.SessionManager
import org.json.JSONArray
import org.json.JSONObject
import org.koin.androidx.viewmodel.ext.android.viewModel


class ActivityJobDetails : AppCompatActivity() {
    private lateinit var binding: ActivityJobDetailsBinding
    private val mainViewModel by viewModel<MainViewModel>()
    private var appliedJobList = ArrayList<JobList>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJobDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.actionBar.toolbarHeaderText.text = "Job Details"
        binding.actionBar.sideMenu.setOnClickListener {
            onBackPressed()
        }
        mainViewModel.getJobDetails(intent.getIntExtra("jobId",0),SessionManager.getInstance(applicationContext).getUserId)
        mainViewModel.getAppliedJobs(SessionManager.getInstance(applicationContext).getUserId)
        binding.applyJobBtn.setOnClickListener {
            if(binding.applyJobBtn.text.toString() =="Apply Now"){
                if(!SessionManager.getInstance(applicationContext).isLogin){
                    startActivity(Intent(applicationContext,LoginActivity::class.java))
                    finish()
                }else{
                    mainViewModel.appJob(SessionManager.getInstance(this).getUserId,intent.getIntExtra("jobId",0))

                }
            }

        }

        observer()
    }

    override fun onResume() {
        super.onResume()
        mainViewModel.getJobDetails(intent.getIntExtra("jobId",0),SessionManager.getInstance(applicationContext).getUserId)
        mainViewModel.getAppliedJobs(SessionManager.getInstance(applicationContext).getUserId)
        observer()

    }
    fun observer(){
        appliedJobList.clear()

        mainViewModel.appliedJobsResponse.observe(this){
            appliedJobList.addAll(it.body()!!.data.jobList)
        }
        mainViewModel.response.observe(this){

            binding.jobTitleDetail.text = it.body()!!.jobDetail.designation
            binding.jobDescDetail.loadData(it.body()!!.jobDetail.description,"","");
            binding.jobDescDetail.settings.javaScriptEnabled = true

            binding.jobExpDetail.loadData(it.body()!!.jobDetail.requirement,"","");
            binding.jobExpDetail.settings.javaScriptEnabled = true

            binding.jobLocDetail.loadData(it.body()!!.jobDetail.cityName.replace("[","").replace("]",""),"","");
            binding.jobLocDetail.settings.javaScriptEnabled = true
           // binding.jobDescDetail.text = Html.fromHtml(Html.fromHtml(it.body()!!.jobDetail.description).toString())
//            binding.jobExpDetail.text = Html.fromHtml(Html.fromHtml(it.body()!!.jobDetail.requirement).toString())
            binding.jobSalaryDetail.text = it.body()!!.jobDetail.salary+"/Month"
//            binding.jobLocDetail.text = Html.fromHtml(Html.fromHtml(it.body()!!.jobDetail.cityName).toString().replace("[","").replace("]",""))
            binding.Location.text = Html.fromHtml(Html.fromHtml(it.body()!!.jobDetail.jobLocation).toString().replace("[","").replace("]",""))

            binding.Experience.text = it.body()!!.jobDetail.experienceNeeded
            binding.postedDate.text = it.body()!!.jobDetail.date
            binding.OfferedSalary.text = it.body()!!.jobDetail.salary+"/Month"
            binding.Qualification.text = it.body()!!.jobDetail.qualificationNeeded
            binding.applyJobBtn.text = it.body()!!.jobDetail.is_apply

            Glide.with(this).load("${IMAGE_BASE_URL}${it.body()!!.jobDetail.logo}").placeholder(R.drawable.tkllogo).circleCrop().into(binding.jobLogo)
        }

        mainViewModel.jobApplyResponse.observe(this){
            if(it.body()!!.status==200){
                showAlert(it.body()!!.msg,"Success")

            }else{
                showAlert(it.body()!!.msg,"Error")

            }
            mainViewModel.getJobDetails(intent.getIntExtra("jobId",0),SessionManager.getInstance(applicationContext).getUserId)



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