package com.tklpvtltd.ui

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModel
import com.data.responseModel.City
import com.tklpvtltd.AuthViewModel
import com.tklpvtltd.MainViewModel
import com.tklpvtltd.databinding.ActivityJobProviderBinding
import com.tklpvtltd.ui.profile.adapter.AlertCityAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class JobProviderRegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityJobProviderBinding
    private var jobCategoryList = ArrayList<String>()
    private var exprence = ArrayList<String>()
    private var exprenceId = ArrayList<Int>()
    private val mainViewModel by viewModel<MainViewModel>()
    private val authViewModel: AuthViewModel by viewModels()
    private var cityResponseList = ArrayList<City>()
    private lateinit var alertCityAdapter: AlertCityAdapter

    private var stateName = ArrayList<String>()
    private var stateId= ArrayList<Int>()
    private var expId = 0
    private var stateId_ =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJobProviderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.actionBar.toolbarHeaderText.text = "Job Provider"
        binding.actionBar.sideMenu.setOnClickListener {
            onBackPressed()
        }
        exprenceId.add(10)
        exprenceId.add(9)
        exprenceId.add(8)
        exprenceId.add(7)
        exprenceId.add(6)
        exprenceId.add(5)
        exprenceId.add(4)
        exprenceId.add(3)
        exprenceId.add(2)
        exprenceId.add(1)

        jobCategoryList.clear()
        jobCategoryList.add("Reasoning")
        jobCategoryList.add("Social Studies")
        jobCategoryList.add("Hindi")
        jobCategoryList.add("Mathematics")
        jobCategoryList.add("Chemistry")
        jobCategoryList.add("Physics")
        jobCategoryList.add("Biology")
        jobCategoryList.add("Human Resources")
        jobCategoryList.add("English")


        exprence.clear()

        exprence.add("1")
        exprence.add("2")
        exprence.add("3")
        exprence.add("4")
        exprence.add("5")
        exprence.add("6")

        authViewModel.getCity()
         binding.actionBar.actionLAy.setOnClickListener {
             binding.layoutLocation.visibility =View.GONE

         }
        binding.layoutLocation.visibility =View.GONE
         binding.cross.setOnClickListener {
             binding.layoutLocation.visibility =View.GONE

         }
        binding.dismiss.setOnClickListener {
            binding.layoutLocation.visibility =View.GONE

        }
        binding.cityJobProvider.setOnFocusChangeListener { v, hasFocus ->
            binding.layoutLocation.visibility =View.VISIBLE
            binding.rvCity1.visibility =View.VISIBLE
            binding.tvLocation.text ="---Select Location---"

            binding.layoutCity.visibility =View.VISIBLE
            binding.searchKeyWordCity.requestFocus()
            authViewModel.getCity()

        }


        binding.cityJobProvider.setOnClickListener {
            binding.layoutLocation.visibility =View.VISIBLE

            binding.rvCity1.visibility =View.VISIBLE

            binding.tvLocation.text ="---Select Location---"

            binding.layoutCity.visibility =View.VISIBLE
            binding.searchKeyWordCity.requestFocus()
            authViewModel.getCity()

        }
        alertCityAdapter = AlertCityAdapter(object : AlertCityAdapter.CallBackJobList{
            override fun getJobData(list: MutableList<City>, position: Int) {
                stateId_ = list[position].id
                binding.cityJobProvider.setText(list[position].name)
                binding.layoutLocation.visibility =View.GONE


            }

        })


        binding.searchKeyWordCity.doOnTextChanged { text, start, before, count ->
            if(text.toString().isNotEmpty()){


                if(alertCityAdapter.searchResult(text.toString()).isNotEmpty()){
                    alertCityAdapter.setData(alertCityAdapter.searchResult(text.toString()).distinct() as MutableList<City>)
                    binding.rvCity1.adapter = alertCityAdapter
                    binding.rvCity1.visibility =View.VISIBLE


                }else{
                    binding.rvCity1.visibility =View.GONE

                }

            }else{

                alertCityAdapter.setData(cityResponseList)
                binding.rvCity1.visibility =View.VISIBLE



            }
        }

//         binding.cityJobProvider.setOnClickListener {
//             binding.cityJobProvider.showDropDown()
//
//         }

        val adapterExp: ArrayAdapter<String> = ArrayAdapter<String>(
            applicationContext,
            R.layout.simple_expandable_list_item_1,
            exprence.distinct()
        )
        binding.expNeed.setThreshold(1)
        binding.expNeed.setAdapter(adapterExp)

        binding.expNeed.setOnTouchListener(View.OnTouchListener { v, event ->
            binding.expNeed.showDropDown()

            false
        })

        binding.expNeed.setOnItemClickListener(AdapterView.OnItemClickListener { parent, arg1, position, arg3 ->

            expId = exprenceId[position]
        })

        binding.cityJobProvider.setOnTouchListener(View.OnTouchListener { v, event ->
            binding.cityJobProvider.showDropDown()

            false
        })

        binding.cityJobProvider.setOnItemClickListener(AdapterView.OnItemClickListener { parent, arg1, position, arg3 ->

            stateId_ = stateId[position]
        })


        val adadpterJobCat: ArrayAdapter<String> = ArrayAdapter<String>(
            applicationContext,
            R.layout.simple_expandable_list_item_1,
            jobCategoryList.distinct()
        )
        binding.jobCategoery.setThreshold(1)
        binding.jobCategoery.setAdapter(adadpterJobCat)

        binding.jobCategoery.setOnTouchListener(View.OnTouchListener { v, event ->
            binding.jobCategoery.showDropDown()

            false
        })

        binding.jobCategoery.setOnItemClickListener(AdapterView.OnItemClickListener { parent, arg1, position, arg3 ->


        })
        binding.jobProviderApply.setOnClickListener {
             if(expId==0){
                showAlert("Please fill all details","Error")
             }else if(binding.phnJobProvider.text.toString().isEmpty()){
                 showAlert("Please fill all details","Error")

             }else if(binding.comNameJobProvider.text.toString().isEmpty()){
                 showAlert("Please fill all details","Error")

             }else if(binding.jobDescJobProvider.text.toString().isEmpty()){
                 showAlert("Please fill all details","Error")

             }else if(binding.qualificationNeeded.text.toString().isEmpty()){
                 showAlert("Please fill all details","Error")

             }else if(binding.phnJobProvider.text.toString().isEmpty()){
                 showAlert("Please fill all details","Error")

             }else if(binding.currentSalaryMonth.text.toString().isEmpty()){

             }else if(binding.selectionProcess.text.toString().isEmpty()){
                 showAlert("Please fill all details","Error")

             }else if(binding.phoneNumber.text.toString().isEmpty()){
                 showAlert("Please fill all details","Error")

             }else if(binding.phoneNumber.text.toString().length!=10){
                 showAlert("Please fill valid phone number","Error")

             }else if(binding.expJobProvider.text.toString().isEmpty()){
                 showAlert("Please fill all details","Error")

             }else if(binding.expJobProvider.text.toString().isEmpty()){
                 showAlert("Please fill all details","Error")

             }else{
                 submitForm(
                     expId.toString(),
                     binding.phnJobProvider.text.toString(),
                     binding.comNameJobProvider.text.toString(),
                     binding.jobDescJobProvider.text.toString(),
                     binding.qualificationNeeded.text.toString(),
                     binding.expNeed.text.toString(),
                     binding.currentSalaryMonth.text.toString(),
                     binding.selectionProcess.text.toString(),
                     stateId_.toString(),
                     binding.phoneNumber.text.toString(),
                     binding.expJobProvider.text.toString(),
                     binding.nameJobProvider.text.toString(),



                     )
                 binding.jobProviderPB.visibility=View.VISIBLE
             }

        }
        mainViewModel.jobProviderResponse.observe(this) {
            showAlert("Application sent successfully", "")
            binding.jobProviderPB.visibility=View.GONE

        }
        authViewModel.cityResponse.observe(this){
            cityResponseList.addAll(it.body()!!.city)
            alertCityAdapter.setData(it.body()!!.city as MutableList<City>)
            binding.rvCity1.adapter = alertCityAdapter
//            for(i in it.body()!!.city){
//                stateName.add(i.name)
//                stateId.add(i.id)
//
//            }
//            val adapterState: ArrayAdapter<String> = ArrayAdapter<String>(
//                applicationContext,
//                R.layout.simple_expandable_list_item_1,
//                stateName.distinct()
//            )
//            binding.cityJobProvider.setThreshold(1)
//            binding.cityJobProvider.setAdapter(adapterState)
        }


    }

    fun submitForm(
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
        mainViewModel.jobProviderForm(
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