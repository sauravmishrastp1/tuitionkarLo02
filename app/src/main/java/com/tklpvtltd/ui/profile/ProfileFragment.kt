package com.tklpvtltd.ui.profile

import android.Manifest
import android.R
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.data.remote.baseApi.BaseInterface
import com.data.remote.baseApi.DataRepository
import com.data.responseModel.*
import com.github.dhaval2404.imagepicker.ImagePicker
import com.tklpvtltd.*
import com.tklpvtltd.databinding.FragmentProfileBinding
import com.tklpvtltd.ui.profile.adapter.*
import com.tklpvtltd.utils.TklFileUtils
import com.tklpvtltd.utils.prefrence.SessionManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.*


class ProfileFragment : Fragment() ,BaseInterface,GetLocation,UpdateProfile{
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private var isResumeUplaod=false
    private var isAadharCardUpload=false
    private var isProfileUpload=false
    protected val PERMISSION_REQUEST_CODE: Int = 101
    private val mainViewModel by viewModel<MainViewModel>()
    private val authViewModel by viewModel<AuthViewModel>()
    private var isAadharClick = false
    private var isResumeClick = false
    private var isPhotoClick = false
    private var countryListId = ArrayList<Int>()
    private var stateName = ArrayList<String>()
    private var stateId= ArrayList<Int>()
    private var exprence = ArrayList<String>()
    private var cityName = ArrayList<String>()
    private var countryid_ = 0
    private var stateId_ = 0
    private var city_id = 0
    private var cityId = ArrayList<Int>()
    private var cityResponseList = ArrayList<City>()
    private var stateResponseList = ArrayList<State>()
    private var countryResponseList = ArrayList<Country>()
    private var profileFile :File?=null
    private var aadharFile :File?=null
    private var resume :File?=null
    private var salaryRange = ArrayList<String>()
    private var profileCityId = 0
    private var profileCountryId =0
    private var profileStateId =0
    private lateinit var alertCityAdapter: AlertCityAdapter
    private lateinit var alertCountryAdapter: AlertCountryAdapter
    private lateinit var alertStateAdapter: AlertStateAdapter
    private var isCountryDropDownSelected =true
    private var isStateDropDownSelected  =true
    private var isCityDropDownSelected =true
    private val myCalendar = Calendar.getInstance()



    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentProfileBinding.inflate(inflater, container, false)
        if(SessionManager.getInstance(requireContext()).isLogin){
            mainViewModel.getJobSeejerProfile(SessionManager.getInstance(requireContext()).getUserId)

        }else{
            showAlert("Please Login","Error")
        }
        authViewModel.updateProfile = this
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







        mainViewModel.baseInterface = this
        authViewModel.getLocation = this
        isAadharClick = false
        isResumeClick = false
        isPhotoClick = false

        binding.rvCountry.visibility =View.GONE
        binding.rvCity.visibility =View.GONE
        binding.rvState.visibility =View.GONE
        binding.layoutLocation.visibility =View.GONE
        binding.cross.setOnClickListener {
            binding.layoutLocation.visibility =View.GONE

        }

        binding.layoutParemt.setOnClickListener {
            binding.layoutLocation.visibility =View.GONE

        }

          binding.stateProfileET.setOnFocusChangeListener { v, hasFocus ->
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


        binding.stateProfileET.setOnClickListener {
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

        binding.cityProfileET.setOnFocusChangeListener { v, hasFocus ->
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
        binding.cityProfileET.setOnClickListener {
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


        binding.countryProfileET.setOnFocusChangeListener { v, hasFocus ->
        binding.layoutLocation.visibility =View.VISIBLE
            binding.rvCity1.visibility =View.GONE
            binding.rvState1.visibility =View.GONE
            binding.rvCountry1.visibility =View.VISIBLE
            binding.tvLocation.text ="---Select Country---"

            binding.layoutState.visibility =View.GONE
            binding.layoutCountry.visibility =View.GONE
            binding.layoutCity.visibility =View.GONE
            binding.searchKeyWordCountry.requestFocus()




        }

        binding.countryProfileET.setOnClickListener {
            binding.layoutLocation.visibility =View.VISIBLE
            binding.rvCity1.visibility =View.GONE
            binding.rvState1.visibility =View.GONE
            binding.rvCountry1.visibility =View.VISIBLE
            binding.tvLocation.text ="---Select Country---"

            binding.layoutState.visibility =View.GONE
            binding.layoutCountry.visibility =View.GONE
            binding.layoutCity.visibility =View.GONE
            binding.searchKeyWordCountry.requestFocus()





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
                binding.countryProfileET.setText(list[position].name)
                binding.layoutLocation.visibility =View.GONE
                isCountryDropDownSelected = true


            }

        })

        alertStateAdapter = AlertStateAdapter(object :AlertStateAdapter.CallBackJobList{
            override fun getJobData(list: MutableList<State>, position: Int) {
                authViewModel.getCity(list[position].id)
                stateId_ = list[position].id
                binding.stateProfileET.setText(list[position].name)
                binding.layoutLocation.visibility =View.GONE
                isStateDropDownSelected = true




            }

        })



        alertCityAdapter = AlertCityAdapter(object :AlertCityAdapter.CallBackJobList{
            override fun getJobData(list: MutableList<City>, position: Int) {
                city_id = list[position].id
                binding.cityProfileET.setText(list[position].name)
                binding.layoutLocation.visibility =View.GONE
                isCityDropDownSelected = true


            }

        })

        binding.profileSave.setOnClickListener {
            if(binding.nameProfileET.text.toString().isEmpty()){
                showAlert("Please Enter Full Name","Error")

            }else if(binding.emailProfileET.text.toString().isEmpty()){
                showAlert("Please Enter Email Address","Error")

            }else if(binding.phoneProfileET.text.toString().isEmpty()){
                showAlert("Please Enter Phone Number","Error")


            }else if(binding.phoneProfileET.text.toString().isEmpty()){
                showAlert("Please Enter Whatsapp Number","Error")


            }else if(binding.dobEt.text.toString().isEmpty()){
                showAlert("Please Enter Date of birth","Error")


            }else if(binding.industryType.text.toString().isEmpty()){
                showAlert("Please Enter Industry type","Error")


            }else if(binding.jobTitle.text.toString().isEmpty()){
                showAlert("Please Enter Job title","Error")


            }else if(binding.totalExperience.text.toString().isEmpty()){
                showAlert("Please Enter total experience","Error")


            }else if(binding.highestQualification.text.toString().isEmpty()){
                showAlert("Please Enter highest qualification","Error")


            }else if(binding.currentSalaryMonth.text.toString().isEmpty()){
                showAlert("Please Enter current salaryMonth","Error")


            }else if(binding.cityProfileET.text.toString().isEmpty()){
                showAlert("Please Enter Country","Error")


            }else if(binding.stateProfileET.text.toString().isEmpty()){
                showAlert("Please Enter state","Error")


            }else if(binding.cityProfileET.text.toString().isEmpty()){
                showAlert("Please Enter city","Error")


            }else if(binding.completeAddress.text.toString().isEmpty()){
                showAlert("Please Enter complete address","Error")


            } else if (authViewModel.profileImage == null) {
                showAlert("Please Upload profile", "Error")


            } else if (authViewModel.aadharCard == null) {
                showAlert("Please Upload Aadhaar card", "Error")


            } else if (authViewModel.resume == null) {
                showAlert("Please Upload Aadhaar card", "Error")


            }else{
                if(countryid_==0){
                    countryid_ =profileCountryId
                }
                if(stateId_ ==0){
                    stateId_ = profileStateId
                }
                if(city_id==0){
                    city_id = profileCityId
                }
                if(binding.cityOtherET.text.toString().isNotEmpty()){
                    city_id =0

                }
                authViewModel.registerModelParameter = RegisterModelParameter(binding.emailProfileET.text.toString(),binding.nameProfileET.text.toString(),binding.phoneProfileET.text.toString(),binding.phoneProfileET.text.toString(),binding.dobEt.text.toString(),binding.industryType.text.toString(),binding.jobTitle.text.toString(),binding.totalExperience.text.toString().toInt(),binding.highestQualification.text.toString(),binding.currentSalaryMonth.text.toString(),city_id,binding.cityOtherET.text.toString(),stateId_,countryid_,binding.completeAddress.text.toString(),SessionManager.getInstance(requireContext()).getUserId.toString())
                if(authViewModel.registerModelParameter!=null){
                    authViewModel.updatePrfile()


                }

            }
        }


        binding.uploadAadharJobForm.setOnClickListener {
            isAadharCardUpload = true
            isResumeUplaod = false
            isProfileUpload=false
            isAadharClick = true

            askPermissions()

        }



        binding.uploadMarkJobForm.setOnClickListener {
            isAadharCardUpload = false
            isResumeUplaod = true
            isProfileUpload=false
            isResumeClick = true
            askPermissions()



        }
        binding.profileImageIV.setOnClickListener {
            isAadharCardUpload = false
            isResumeUplaod = false
            isProfileUpload=true
            isPhotoClick=true
            askPermissions()

        }
        val adapterCurrentSalary: ArrayAdapter<String> = ArrayAdapter<String>(
            requireContext(),
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
            requireContext(),
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

        binding.dobEt.setOnClickListener {
            showDatePicker()
        }

        binding.dobEt.setOnFocusChangeListener { v, hasFocus ->
            showDatePicker()
        }

        return  binding.root

    }

    override fun onStart() {
        super.onStart()
        observe()

    }
    override fun onResume() {
        super.onResume()
        authViewModel.getCountry()
        observe()

    }
    private fun observe(){
        var countryListName = ArrayList<String>()

        countryListId.clear()
        stateName.clear()
        stateId.clear()
        cityName.clear()
        cityId.clear()

        authViewModel.allCountryResponse.observe(viewLifecycleOwner){
            for(i in it.body()!!.country){
                countryListName.add(i.name)
                countryListId.add(i.id)

            }
            countryResponseList.addAll(it.body()!!.country)
            alertCountryAdapter.setData(it.body()!!.country as MutableList<Country>)
            binding.rvCountry1.adapter = alertCountryAdapter


        }
        authViewModel.stateResponse.observe(viewLifecycleOwner){
            for(i in it.body()!!.state){
                stateName.add(i.name)
                stateId.add(i.id)

            }
           stateResponseList.addAll(it.body()!!.state)
            alertStateAdapter.setData(it.body()!!.state as MutableList<State>)
            binding.rvState1.adapter = alertStateAdapter



        }
        authViewModel.cityResponse.observe(viewLifecycleOwner){
            for(i in it.body()!!.city){
                cityName.add(i.name)
                cityId.add(i.id)

            }
            cityResponseList.addAll(it.body()!!.city)
            alertCityAdapter.setData(it.body()!!.city as MutableList<City>)
            binding.rvCity1.adapter = alertCityAdapter

        }


    }
    private fun showAlert(message: String, title:String) {
        val alertDialog = AlertDialog.Builder(requireContext()).create()
        alertDialog.setTitle(title)
        alertDialog.setCancelable(false)
        alertDialog.setMessage(message)
        alertDialog.setButton(
            AlertDialog.BUTTON_POSITIVE, "OK"
        ) { dialog, which -> dialog.dismiss()
        }
        alertDialog.show()



    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun askPermissions() {

        val permissions = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_MEDIA_IMAGES,
        )
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(
                    Objects.requireNonNull<Any>(
                        requireActivity()
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
    private fun cameraIntent() {

        val intent: Intent = Intent()
        intent.setType("image/*")
        intent.setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {

            var result = data?.data!!
            if(isResumeUplaod){
                authViewModel.resume = File(TklFileUtils.getPath(requireContext(), result))
                authViewModel.updateResume = File(TklFileUtils.getPath(requireContext(), result))
                binding.imgresume.setImageURI(result)

            }else if(isAadharCardUpload){
                authViewModel.aadharCard = File(TklFileUtils.getPath(requireContext(), result))
                authViewModel.updateAadharCard = File(TklFileUtils.getPath(requireContext(), result))
                binding.imgaadhar.setImageURI(result)

            }else if(isProfileUpload){
                authViewModel.profileImage = File(TklFileUtils.getPath(requireContext(), result))
                authViewModel.updateProfileImage = File(TklFileUtils.getPath(requireContext(), result))
                binding.profileImageIV.setImageURI(result)

            }




        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Cancelled", Toast.LENGTH_SHORT).show()
        }

    }

    fun convertImageUrlToFile(imageUrl: String):File {
        var file: File? = null
        Thread {
            try {
                // Download the image from the URL
                val url = URL(imageUrl)
                val connection = url.openConnection() as HttpURLConnection
                connection.doInput = true
                connection.connect()
                val input: InputStream = connection.inputStream

                // Create a temporary file to save the image
                val outputDir: File = requireActivity().cacheDir
                file = File.createTempFile("image", "jpg", outputDir)

                // Write the image data to the file
                val output = FileOutputStream(file)
                val buffer = ByteArray(1024)
                var bytesRead: Int
                while (input.read(buffer).also { bytesRead = it } != -1) {
                    output.write(buffer, 0, bytesRead)
                }
                output.close()
                input.close()

                // Display the image in the ImageView

            } catch (e: IOException) {
                e.printStackTrace()
            }
        }.start()
        return file!!.absoluteFile
    }



    override fun getUpdateProfileDetails(it: Response<LoginResponse>) {
        authViewModel.getCountry1()

            authViewModel.getSate1(it.body()!!.user.country.toInt())
            authViewModel.getCity1(it.body()!!.user.state.toInt())
           profileCityId= it.body()!!.user.city.toInt()
           profileStateId= it.body()!!.user.state.toInt()
           profileCountryId= it.body()!!.user.country.toInt()
           countryid_ = it.body()!!.user.country.toInt()
           stateId_ = it.body()!!.user.state.toInt()
           city_id = it.body()!!.user.city.toInt()
            binding.completeAddress.setText(it.body()!!.user.fullAddress)
            binding.nameProfileET.setText(it.body()!!.user.fullName)
            binding.emailProfileET.setText(it.body()!!.user.emailId)
            binding.phoneProfileET.setText(it.body()!!.user.mobileNo)


            binding.dobEt.setText(it.body()!!.user.dob.replace("/","-"))
            binding.industryType.setText(it.body()!!.user.industryName)
            binding.jobTitle.setText(it.body()!!.user.jobTitle)
            binding.totalExperience.setText(it.body()!!.user.totalExperience)
            binding.highestQualification.setText(it.body()!!.user.highestQualification)
            binding.currentSalaryMonth.setText(it.body()!!.user.currentSalary)


        try {
            if(!isPhotoClick){
                Glide.with(requireContext()).load(DataRepository.IMAGE_BASE_URL+it.body()!!.user.profileImage).into(binding.profileImageIV)

            }
            if(!isResumeClick){
                Glide.with(requireContext()).load(DataRepository.IMAGE_BASE_URL+it.body()!!.user.resume).into(binding.imgresume)

            }
            if(!isAadharClick){
                Glide.with(requireContext()).load(DataRepository.IMAGE_BASE_URL+it.body()!!.user.aadhar).into(binding.imgaadhar)

            }


            }catch (e:Exception){

            }
        authViewModel.profileImage  =File(TklFileUtils.getPath(requireContext(), Uri.parse(DataRepository.IMAGE_BASE_URL+it.body()!!.user.profileImage)))
        authViewModel.aadharCard  = File(TklFileUtils.getPath(requireContext(), Uri.parse(DataRepository.IMAGE_BASE_URL+it.body()!!.user.aadhar)))
        authViewModel.resume  = File(TklFileUtils.getPath(requireContext(), Uri.parse(DataRepository.IMAGE_BASE_URL+it.body()!!.user.resume)))

        for(i in cityResponseList){
            if(i.id == it.body()!!.user.city.toInt()){
                binding.cityProfileET.setText(i.name)

            }
        }


    }

    override fun getstate(it: Response<StateResponse>) {
        stateResponseList.addAll(it.body()!!.state)



        for(i in it.body()!!.state){
            if(i.id== profileStateId){
                binding.stateProfileET.setText(i.name)

            }
        }

    }

    override fun getCity(it: Response<CityResponse>) {
        cityResponseList.addAll(it.body()!!. city)


        for(i in it.body()!!.city){
            if(i.id== profileCityId){
                binding.cityProfileET.setText(i.name)

            }
        }


    }

    override fun getCountry(it: Response<AllCountryResponse>) {
        countryResponseList.addAll(it.body()!!. country)

        for(i in it.body()!!.country){
            if(i.id== profileCountryId){
                binding.countryProfileET.setText(i.name)

            }
        }


    }
    fun getFile(url:String):File{
       // return File(URL(url).toURI());
        var outputFile :File?=null
        val thread = Thread {
            try {

                val url = URL(url)
                val connection = url.openConnection() as HttpURLConnection
                connection.doInput = true
                connection.connect()
                val input = connection.inputStream

                outputFile = File(requireActivity().cacheDir, "image.jpg")
                val output = FileOutputStream(outputFile)

                val buffer = ByteArray(1024)
                var bytesRead: Int
                while (input.read(buffer).also { bytesRead = it } != -1) {
                    output.write(buffer, 0, bytesRead)
                }
                output.close()
                input.close()
                //Your code goes here
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }

        thread.start()
        return  File(outputFile!!.absolutePath)

    }

    override fun updateProfile() {
        showAlert("Update Successfully","Success")

    }

    fun alertDilogeLocation(isCounty:Boolean,isCity:Boolean,isState:Boolean,serechKeyWord:String) {
        val builder = AlertDialog.Builder(requireContext())
        var alert_dialog: AlertDialog? = null
        val layoutInflater = layoutInflater
        val customView = layoutInflater.inflate(com.tklpvtltd.R.layout.layout_choose_location ,null)
        var rvCountry = customView.findViewById<RecyclerView>(com.tklpvtltd.R.id.rvCountry)
        var rvState= customView.findViewById<RecyclerView>(com.tklpvtltd.R.id.rvCountry)
        var rvCity = customView.findViewById<RecyclerView>(com.tklpvtltd.R.id.rvCountry)
        if(isCity){
            rvCountry.visibility=View.GONE
            rvState.visibility=View.GONE
            rvCity.visibility=View.GONE
        }

        if(isCounty){
            rvCountry.visibility=View.VISIBLE
            rvState.visibility=View.GONE
            rvCity.visibility=View.GONE
        }
        if(isState){
            rvCountry.visibility=View.GONE
            rvState.visibility=View.VISIBLE
            rvCity.visibility=View.GONE
        }
         rvCountry.adapter = alertCountryAdapter
         rvState.adapter = alertStateAdapter
         rvCity.adapter = alertCityAdapter
        builder.setView(customView)
        alert_dialog = builder.create()
        alert_dialog.setCancelable(true)
        alert_dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))




        alert_dialog.show()

    }

    fun showDatePicker() {
        val date = DatePickerDialog.OnDateSetListener { view, year, month, day ->
            binding.dobEt.setText("${day}-${month + 1}-${year}")

        }
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            date,
            myCalendar.get(Calendar.YEAR),
            myCalendar.get(Calendar.MONTH),
            myCalendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()


    }


}