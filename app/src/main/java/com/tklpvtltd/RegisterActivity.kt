package com.tklpvtltd

import android.Manifest
import android.R
import android.app.Activity
import android.app.DatePickerDialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.*
import android.os.StrictMode.VmPolicy
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import com.data.responseModel.City
import com.data.responseModel.Country
import com.data.responseModel.RegisterModelParameter
import com.data.responseModel.State
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.tklpvtltd.databinding.ActivityRegisterBinding
import com.tklpvtltd.ui.profile.adapter.AlertCityAdapter
import com.tklpvtltd.ui.profile.adapter.AlertCountryAdapter
import com.tklpvtltd.ui.profile.adapter.AlertStateAdapter
import com.tklpvtltd.utils.TklFileUtils
import com.tklpvtltd.utils.prefrence.SessionManager
import java.io.*
import java.util.*
import kotlin.collections.ArrayList

class RegisterActivity : AppCompatActivity() {
    private val authViewModel: AuthViewModel by viewModels()
    private var isResumeUplaod = false
    private var isAadharCardUpload = false
    private var isProfileUpload = false
    private var countryListId = ArrayList<Int>()
    private var stateName = ArrayList<String>()
    private var stateId = ArrayList<Int>()
    private var exprence = ArrayList<String>()
    private var cityName = ArrayList<String>()
    protected val PERMISSION_REQUEST_CODE: Int = 101
    private var countryid_ = 0
    private var stateId_ = 0
    private var city_id = 0
    private var salaryRange = ArrayList<String>()


    protected val CAMERA_REQUEST = 0
    protected val PDF_REQUEST = 3
    protected val GALLERY_PICTURE = 1
    private val pictureActionIntent: Intent? = null
    var bitmap: Bitmap? = null
    val PIC_CROP = 2
    var f: File? = null
    var selectedImage: Uri? = null
    val PIC_CROP_GALLERY = 3
    var selectedImagePath = ""
    private val REQUEST_WRITE_PERMISSION = 786

    private var cityId = ArrayList<Int>()
    private val myCalendar = Calendar.getInstance()

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var alertCityAdapter: AlertCityAdapter
    private lateinit var alertCountryAdapter: AlertCountryAdapter
    private lateinit var alertStateAdapter: AlertStateAdapter
    private var cityResponseList = ArrayList<City>()
    private var stateResponseList = ArrayList<State>()
    private var countryResponseList = ArrayList<Country>()
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        exprence.clear()
         salaryRange.clear()
        exprence.add("0")
        exprence.add("1")
        exprence.add("2")
        exprence.add("3")
        exprence.add("4")
        exprence.add("7")
        exprence.add("8")
        exprence.add("9")
        exprence.add("10")
        exprence.add("11")
        exprence.add("12")
        exprence.add("13")
        exprence.add("14")
        exprence.add("15")
        exprence.add("16")
        exprence.add("17")
        exprence.add("18")
        exprence.add("19")
        exprence.add("20")

        salaryRange.add("< Rs 10000")
        salaryRange.add("Between Rs 10000 to Rs 50000")
        salaryRange.add("Between Rs 50000 to Rs 1 Lakh")
        salaryRange.add("Between Rs 1 Lakh to Rs 2 Lakh")
        salaryRange.add("Between Rs 2 Lakh to Rs 10 Lakh")
        salaryRange.add("Between Rs 10 Lakh to Rs 50 Lakh")
        salaryRange.add("Between Rs 50 Lakh to Rs 1 Crore")
        salaryRange.add("Above 1 Crore")


        if (SessionManager.getInstance(applicationContext).isLogin) {
            binding.layoutOption.visibility = View.GONE
        } else {
            binding.layoutOption.visibility = View.VISIBLE

        }
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                android.Manifest.permission.CAMERA,
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            ),
            0
        )

        binding.cvLogin.setOnClickListener {
            startActivity(Intent(applicationContext, LoginActivity::class.java))
            finish()


        }
        binding.passwordRegister.setOnClickListener {
            showDatePicker()
        }
        binding.cross.setOnClickListener {
            binding.layoutLocation.visibility =View.GONE

        }
         binding.Country.setOnFocusChangeListener { v, hasFocus ->
             binding.layoutLocation.visibility =View.VISIBLE
             binding.rvCity1.visibility =View.GONE
             binding.rvState1.visibility =View.GONE
             binding.rvCountry1.visibility =View.VISIBLE
             binding.tvLocation.text ="---Select Country---"

             binding.layoutState.visibility =View.GONE
             binding.layoutCountry.visibility =View.GONE
             binding.layoutCity.visibility =View.GONE
             binding.searchKeyWordCountry.requestFocus()
             authViewModel.getCountry()
         }

        binding.state.setOnFocusChangeListener { v, hasFocus ->
            binding.layoutLocation.visibility =View.VISIBLE
            binding.rvCity1.visibility =View.GONE

            binding.rvState1.visibility =View.VISIBLE
            binding.rvCountry1.visibility =View.GONE
            binding.tvLocation.text ="---Select State---"

            binding.layoutState.visibility =View.VISIBLE
            binding.layoutCountry.visibility =View.GONE
            binding.layoutCity.visibility =View.GONE
            binding.searchKeyWordState.requestFocus()
            authViewModel.getSate(countryid_)
        }

        binding.city.setOnFocusChangeListener { v, hasFocus ->
            binding.layoutLocation.visibility =View.VISIBLE
            binding.rvCity1.visibility =View.VISIBLE
            binding.rvState1.visibility =View.GONE
            binding.rvCountry1.visibility =View.GONE
            binding.tvLocation.text ="---Select City---"

            binding.layoutState.visibility =View.GONE
            binding.layoutCountry.visibility =View.GONE
            binding.layoutCity.visibility =View.VISIBLE
            binding.searchKeyWordCity.requestFocus()
            authViewModel.getCity(stateId_)
        }


        binding.city.setOnClickListener {
            binding.layoutLocation.visibility =View.VISIBLE

            binding.rvCity1.visibility =View.VISIBLE
            binding.rvState1.visibility =View.GONE
            binding.rvCountry1.visibility =View.GONE
            binding.tvLocation.text ="---Select City---"

            binding.layoutState.visibility =View.GONE
            binding.layoutCountry.visibility =View.GONE
            binding.layoutCity.visibility =View.VISIBLE
            binding.searchKeyWordCity.requestFocus()
            authViewModel.getCity(stateId_)
        }
        binding.searchKeyWordState.doOnTextChanged { text, start, before, count ->
            if(text.toString().isNotEmpty()){
                if(alertStateAdapter.searchResult(text.toString()).isNotEmpty()){
                    alertStateAdapter.setData(alertStateAdapter.searchResult(text.toString()).distinct() as MutableList<State>)
                    binding.rvState1.adapter = alertStateAdapter
                    binding.rvState1.visibility =View.VISIBLE


                }else{

                    binding.rvState1.visibility =View.GONE
                }


            }else{
                alertStateAdapter.setData(stateResponseList)

                binding.rvState1.visibility =View.VISIBLE


            }
        }


        binding.searchKeyWordCountry.doOnTextChanged { text, start, before, count ->
            if(text.toString().isNotEmpty()){

                if(alertCountryAdapter.searchResult(text.toString()).isNotEmpty()){
                    alertCountryAdapter.setData(alertCountryAdapter.searchResult(text.toString()).distinct() as MutableList<Country>)
                    binding.rvCountry1.adapter = alertStateAdapter
                    binding.rvCountry1.visibility =View.VISIBLE


                }else{
                    binding.rvCountry1.visibility =View.GONE
                }




            }else{
                alertCountryAdapter.setData(countryResponseList)
                binding.rvCountry1.visibility =View.VISIBLE


            }
        }


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







        alertCountryAdapter = AlertCountryAdapter(object :AlertCountryAdapter.CallBackJobList{
            override fun getJobData(list: MutableList<Country>, position: Int) {
                authViewModel.getSate(list[position].id)
                countryid_ = list[position].id
                binding.Country.setText(list[position].name)
                binding.layoutLocation.visibility =View.GONE


            }

        })

        alertStateAdapter = AlertStateAdapter(object :AlertStateAdapter.CallBackJobList{
            override fun getJobData(list: MutableList<State>, position: Int) {
                authViewModel.getCity(list[position].id)
                stateId_ = list[position].id
                binding.state.setText(list[position].name)
                binding.layoutLocation.visibility =View.GONE
             //   isStateDropDownSelected = true




            }

        })



        alertCityAdapter = AlertCityAdapter(object :AlertCityAdapter.CallBackJobList{
            override fun getJobData(list: MutableList<City>, position: Int) {
                city_id = list[position].id
                binding.city.setText(list[position].name)
                binding.layoutLocation.visibility =View.GONE


            }

        })

        binding.signUp.setOnClickListener {
            if (binding.fullName.text.toString().isEmpty()) {
                showAlert("Please Enter Full Name", "Error")

            } else if (binding.emailRegister.text.toString().isEmpty()) {
                showAlert("Please Enter Email Address", "Error")

            } else if (binding.phoneNumber.text.toString().isEmpty()) {
                showAlert("Please Enter Phone Number", "Error")


            } else if (binding.phoneNumber.text.toString().length != 10) {
                showAlert("Please Ente Valid Phone Number", "Error")


            } else if (binding.whatsappNo.text.toString().isEmpty()) {
                showAlert("Please Enter Whatsapp Number", "Error")


            } else if (binding.whatsappNo.text.toString().length != 10) {
                showAlert("Please Enter Valid Whatsapp Number", "Error")


            } else if (binding.passwordRegister.text.toString().isEmpty()) {
                showAlert("Please Enter Date of birth", "Error")


            } else if (binding.industryType.text.toString().isEmpty()) {
                showAlert("Please Enter Industry type", "Error")


            } else if (binding.jobTitle.text.toString().isEmpty()) {
                showAlert("Please Enter Job title", "Error")


            } else if (binding.totalExperience.text.toString().isEmpty()) {
                showAlert("Please Enter total experience", "Error")


            } else if (binding.highestQualification.text.toString().isEmpty()) {
                showAlert("Please Enter highest qualification", "Error")


            } else if (binding.currentSalaryMonth.text.toString().isEmpty()) {
                showAlert("Please Enter current salaryMonth", "Error")


            } else if (binding.Country.text.toString().isEmpty()) {
                showAlert("Please Enter Country", "Error")


            } else if (binding.state.text.toString().isEmpty()) {
                showAlert("Please Enter state", "Error")


            } else if (binding.city.text.toString().isEmpty()) {
                showAlert("Please Enter city", "Error")


            } else if (binding.completeAddress.text.toString().isEmpty()) {
                showAlert("Please Enter complete address", "Error")


            } else if (authViewModel.profileImage == null) {
                showAlert("Please Upload profile", "Error")


            } else if (authViewModel.aadharCard == null) {
                showAlert("Please Upload Aadhar card", "Error")


            } else if (authViewModel.resume == null) {
                showAlert("Please Upload Aadhar card", "Error")


            } else {
                if(binding.cityOther.text.toString().isNotEmpty()){
                    city_id =0

                }
                authViewModel.registerModelParameter = RegisterModelParameter(
                    binding.emailRegister.text.toString(),
                    binding.fullName.text.toString(),
                    binding.phoneNumber.text.toString(),
                    binding.whatsappNo.text.toString(),
                    binding.passwordRegister.text.toString(),
                    binding.industryType.text.toString(),
                    binding.jobTitle.text.toString(),
                    binding.totalExperience.text.toString().toInt(),
                    binding.highestQualification.text.toString(),
                    binding.currentSalaryMonth.text.toString(),
                    countryid_,
                    binding.cityOther.text.toString(),
                    stateId_,
                    city_id,
                    binding.completeAddress.text.toString(),
                    ""
                )
                if (authViewModel.registerModelParameter != null) {
                    authViewModel.register()


                }
                binding.progressBar.visibility = View.VISIBLE

            }
        }

        binding.uploadAadharJobForm.setOnClickListener {
            isAadharCardUpload = true
            isResumeUplaod = false
            isProfileUpload = false
            askPermissions()

        }



        binding.uploadMarkJobForm.setOnClickListener {
            isAadharCardUpload = false
            isResumeUplaod = true
            isProfileUpload = false
            askPermissions()


        }
        binding.profileImageIV.setOnClickListener {
            isAadharCardUpload = false
            isResumeUplaod = false
            isProfileUpload = true
            askPermissions()

        }

        val adapterCurrentSalary: ArrayAdapter<String> = ArrayAdapter<String>(
            applicationContext,
            R.layout.simple_expandable_list_item_1,
            salaryRange.distinct()
        )
        binding.currentSalaryMonth.setThreshold(1)
        binding.currentSalaryMonth.setAdapter(adapterCurrentSalary)

        binding.currentSalaryMonth.setOnTouchListener(View.OnTouchListener { v, event ->
            binding.currentSalaryMonth.showDropDown()

            false
        })
        binding.currentSalaryMonth.setOnItemClickListener(AdapterView.OnItemClickListener { parent, arg1, position, arg3 ->


        })
        val adapterExp: ArrayAdapter<String> = ArrayAdapter<String>(
            applicationContext,
            R.layout.simple_expandable_list_item_1,
            exprence.distinct()
        )
        binding.totalExperience.setThreshold(1)
        binding.totalExperience.setAdapter(adapterExp)

        binding.totalExperience.setOnTouchListener(View.OnTouchListener { v, event ->
            binding.totalExperience.showDropDown()

            false
        })

        binding.totalExperience.setOnItemClickListener(AdapterView.OnItemClickListener { parent, arg1, position, arg3 ->


        })





    }

    override fun onResume() {
        super.onResume()
        authViewModel.getCountry()

        observe()


    }

    private fun cameraIntent() {
//        ImagePicker.with(this)
//            .crop(16F, 16F)
//            .compress(1024)
//            .maxResultSize(700, 700)
//            .start()

        val intent: Intent = Intent()
        intent.setType("image/*")
        intent.setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1)


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {

            var result = data?.data!!
            if (isResumeUplaod) {
                authViewModel.resume = File(TklFileUtils.getPath(applicationContext, result))
                binding.imgresume.setImageURI(result)

            } else if (isAadharCardUpload) {
                authViewModel.aadharCard = File(TklFileUtils.getPath(applicationContext, result))
                binding.imgaadhar.setImageURI(result)

            } else if (isProfileUpload) {
                authViewModel.profileImage = File(TklFileUtils.getPath(applicationContext, result))
                binding.profileImageIV.setImageURI(result)

            }


        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(applicationContext, ImagePicker.getError(data), Toast.LENGTH_SHORT)
                .show()
        } else {
            Toast.makeText(applicationContext, "Cancelled", Toast.LENGTH_SHORT).show()
        }

    }

    fun observe() {
        var countryListName = ArrayList<String>()

        countryListId.clear()
        stateName.clear()
        stateId.clear()
        cityName.clear()
        cityId.clear()
        authViewModel.allCountryResponse.observe(this) {
            countryResponseList.addAll(it.body()!!.country)
            alertCountryAdapter.setData(it.body()!!.country as MutableList<Country>)
            binding.rvCountry1.adapter = alertCountryAdapter

        }
        authViewModel.stateResponse.observe(this) {
             stateResponseList.addAll(it.body()!!.state)
            alertStateAdapter.setData(it.body()!!.state as MutableList<State>)
            binding.rvState1.adapter = alertStateAdapter


        }
        authViewModel.cityResponse.observe(this) {
            cityResponseList.addAll(it!!.body()!!.city)
            alertCityAdapter.setData(it.body()!!.city as MutableList<City>)
            binding.rvCity1.adapter = alertCityAdapter


        }
        authViewModel.registerResponse.observe(this) {
            binding.progressBar.visibility=View.GONE

            val responseBodyString = it.body()?.string()

            val jsonObject :JsonObject? = Gson().fromJson(responseBodyString, JsonObject::class.java)
            if (jsonObject != null) {
                val value = jsonObject.get("status").asInt
                val msg = jsonObject.get("msg").asString

                if (value == 201) {
                    showAlertRegisterSucess("Already Registered", "Error")

                } else if(value==203) {
                    showAlertRegisterSucess("${msg}", "Format error")

                }else if(value==200){
                    showAlertRegisterSucess("Register successfully", "Success")

                }

            }


        }


    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun askPermissions() {

        val permissions = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_MEDIA_IMAGES,

        )
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(
                    Objects.requireNonNull<Any>(
                        applicationContext
                    ) as Context, permission
                ) == PackageManager.PERMISSION_DENIED
            ) {
                requestPermissions(
                    permissions,
                    PERMISSION_REQUEST_CODE
                )
                cameraIntent()

                return
            }

        }
        cameraIntent()


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

    fun showDatePicker() {
        val date = DatePickerDialog.OnDateSetListener { view, year, month, day ->
            binding.passwordRegister.setText("${year}-${month + 1}-${day}")

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

    fun showAlertRegisterSucess(message: String, title: String) {
        val alertDialog = AlertDialog.Builder(this).create()
        alertDialog.setTitle(title)
        alertDialog.setMessage(message)
        alertDialog.setButton(
            AlertDialog.BUTTON_POSITIVE, "OK"
        ) { dialog, which ->
            dialog.dismiss()
            binding.progressBar.visibility=View.GONE

        }
        alertDialog.show()

    }


}