package com.tklpvtltd.ui.home

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.data.responseModel.AppliedJobsResponse
import com.data.responseModel.JobList
import com.data.responseModel.SliderData
import com.github.dhaval2404.imagepicker.ImagePicker
import com.smarteist.autoimageslider.SliderView
import com.tklpvtltd.MainViewModel
import com.tklpvtltd.R
import com.tklpvtltd.RegisterActivity
import com.tklpvtltd.databinding.FragmentHomeBinding
import com.tklpvtltd.ui.ActivityJobDetails
import com.tklpvtltd.ui.CareerAtTklRegisterActivity
import com.tklpvtltd.ui.JobProviderRegisterActivity
import com.tklpvtltd.ui.adapter.JobListAdapter
import com.tklpvtltd.ui.adapter.SliderAdapter
import com.tklpvtltd.utils.prefrence.SessionManager
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {
    private val homeViewModel by viewModel<HomeViewModel>()
    private var _binding: FragmentHomeBinding? = null
    private lateinit var jobListAdapter: JobListAdapter
    private var jobList = ArrayList<JobList>()
    private var appliedJobList = ArrayList<JobList>()
    private val mainViewModel by viewModel<MainViewModel>()
    private val binding get() = _binding!!
    var url1 = "https://tklpvtltd.com/upload/home-page/slider/slider_image_1676487651.jpeg"
    var url2 = "https://tklpvtltd.com/upload/home-page/slider/slider_image_1676490099.jpeg"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        banner()

        mainViewModel.getAppliedJobs(SessionManager.getInstance(requireContext()).getUserId)

        homeViewModel.getJobLIst(0,"","",SessionManager.getInstance(requireContext()).getUserId)
          observer()
        jobListAdapter = JobListAdapter(object :JobListAdapter.CallBackJobList{
            override fun getJobData(list: MutableList<JobList>, position: Int) {
                startActivity(Intent(requireContext(),ActivityJobDetails::class.java).apply {

                    putExtra("jobId",list[position].id)
                    putExtra("isApplied",list[position].isAppliedJob)
                })
            }

        });
        binding.jobSeekerLinLay.setOnClickListener {
            startActivity(Intent(requireContext(),RegisterActivity::class.java))
        }

        binding.jobProviderLinLay.setOnClickListener {
            startActivity(Intent(requireContext(),JobProviderRegisterActivity::class.java))
        }
        binding.pvtTutionLinLay.setOnClickListener {
            startActivity(Intent(requireContext(),CareerAtTklRegisterActivity::class.java))

        }
        binding.filter.setOnClickListener {
            alertDilogeApplyFilter()
        }
        binding.swipRefresh.setOnRefreshListener {
            homeViewModel.getJobLIst(0,"","",SessionManager.getInstance(requireContext()).getUserId)
            binding.swipRefresh.isRefreshing = false

        }

        return root
    }

    override fun onResume() {
        super.onResume()

        if(SessionManager.getInstance(requireContext()).isLogin){
            binding.layout01.visibility=View.GONE
            binding.vieq01.visibility=View.GONE
        }else{
            binding.layout01.visibility=View.VISIBLE
            binding.vieq01.visibility=View.VISIBLE

        }

    }
    fun observer(){
//        Toast.makeText(requireContext(),SessionManager.getInstance(requireContext()).getUserId.toString(),Toast.LENGTH_SHORT).show()
        appliedJobList.clear()

        mainViewModel.appliedJobsResponse.observe(viewLifecycleOwner){
            appliedJobList.addAll(it.body()!!.data.jobList)
        }
        binding.progressBar.visibility=View.VISIBLE
        jobList.clear()

        homeViewModel.jobListResponse.observe(viewLifecycleOwner){


            binding.progressBar.visibility=View.GONE
            try {
                if(it.body()!!.jobList.isNotEmpty()){
                    binding.tvDataNotFound.visibility =View.GONE

                    jobList.addAll(it.body()!!.jobList)
                if(jobList.isNotEmpty()){
                    jobListAdapter.setData(jobList)
                    binding.rvJobList.adapter = jobListAdapter
                    binding.tvDataNotFound.visibility =View.GONE
                    binding.rvJobList.visibility =View.VISIBLE


                }else{
                    binding.rvJobList.visibility =View.GONE

                    binding.tvDataNotFound.visibility =View.VISIBLE
                }
            }else{
                    binding.rvJobList.visibility =View.GONE

                    binding.tvDataNotFound.visibility =View.VISIBLE

                }
            }catch (e:Exception){
                binding.rvJobList.visibility =View.GONE

                binding.tvDataNotFound.visibility =View.VISIBLE

            }



        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    fun banner(){
        val sliderDataArrayList: ArrayList<SliderData> = ArrayList()

        // initializing the slider view.

        // initializing the slider view.

        // adding the urls inside array list

        // adding the urls inside array list
        sliderDataArrayList.add(SliderData(url1))
        sliderDataArrayList.add(SliderData(url2))

        // passing this array list inside our adapter class.

        // passing this array list inside our adapter class.
        val adapter = SliderAdapter(requireContext(), sliderDataArrayList)

        // below method is used to set auto cycle direction in left to
        // right direction you can change according to requirement.

        // below method is used to set auto cycle direction in left to
        // right direction you can change according to requirement.
        binding.slider.autoCycleDirection = SliderView.LAYOUT_DIRECTION_LTR

        // below method is used to
        // setadapter to sliderview.

        // below method is used to
        // setadapter to sliderview.
        binding.slider.setSliderAdapter(adapter)

        // below method is use to set
        // scroll time in seconds.

        // below method is use to set
        // scroll time in seconds.
        binding.slider.scrollTimeInSec = 3

        // to set it scrollable automatically
        // we use below method.

        // to set it scrollable automatically
        // we use below method.
        binding.slider.isAutoCycle = true

        // to start autocycle below method is used.

        // to start autocycle below method is used.
        binding.slider.startAutoCycle()

    }

    private fun cameraIntent() {
        ImagePicker.with(requireActivity())
            .crop(16F, 16F)
            .compress(1024)
            .maxResultSize(700, 700)
            .start()

    }

    fun alertDilogeApplyFilter() {
        val builder = AlertDialog.Builder(requireContext())
        var alert_dialog: AlertDialog? = null
        val layoutInflater = layoutInflater
        val customView = layoutInflater.inflate(R.layout.layout_apply_filter, null)
        val cvApplyFilter = customView.findViewById<CardView>(R.id.cvApplyFilter)
        val imgKeyWordSearch = customView.findViewById<ImageView>(R.id.imgSearch)
        val imgLocationSearch = customView.findViewById<ImageView>(R.id.imgLocationSearch)



        builder.setView(customView)
        alert_dialog = builder.create()
        alert_dialog.setCancelable(true)
        alert_dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        imgKeyWordSearch.setOnClickListener {
            homeViewModel.getJobLIst(0,customView.findViewById<EditText>(R.id.keyWord).text.toString(),"",SessionManager.getInstance(requireContext()).getUserId)
//            jobListAdapter.setData(jobListAdapter.searchResult(customView.findViewById<EditText>(R.id.keyWord).text.toString()))
//             binding.rvJobList.adapter = jobListAdapter
            alert_dialog?.dismiss()
            jobList.clear()



        }
        imgLocationSearch .setOnClickListener {
            homeViewModel.getJobLIst(0,"",customView.findViewById<EditText>(R.id.location).text.toString(),SessionManager.getInstance(requireContext()).getUserId,)
//            jobListAdapter.setData(jobListAdapter.searchResult(customView.findViewById<EditText>(R.id.keyWord).text.toString()))
//            binding.rvJobList.adapter = jobListAdapter
            alert_dialog?.dismiss()
                jobList.clear()


        }

        var postDate =0
        customView.findViewById<RadioGroup>(R.id.radioGroup).setOnCheckedChangeListener { group, checkedId ->
            if(checkedId == customView.findViewById<RadioButton>(R.id.lastHour).id){
                postDate =1
                homeViewModel.getJobLIst(postDate,customView.findViewById<EditText>(R.id.keyWord).text.toString(),customView.findViewById<EditText>(R.id.location).text.toString(),SessionManager.getInstance(requireContext()).getUserId)
                alert_dialog?.dismiss()

            }else  if(checkedId == customView.findViewById<RadioButton>(R.id.last24Hour).id){
                postDate = 24
                homeViewModel.getJobLIst(postDate,customView.findViewById<EditText>(R.id.keyWord).text.toString(),customView.findViewById<EditText>(R.id.location).text.toString(),SessionManager.getInstance(requireContext()).getUserId)
                alert_dialog?.dismiss()

            }else  if(checkedId == customView.findViewById<RadioButton>(R.id.last14Hour).id){
                postDate = 14
                homeViewModel.getJobLIst(postDate,customView.findViewById<EditText>(R.id.keyWord).text.toString(),customView.findViewById<EditText>(R.id.location).text.toString(),SessionManager.getInstance(requireContext()).getUserId)
                alert_dialog?.dismiss()

            }else  if(checkedId == customView.findViewById<RadioButton>(R.id.last7Hour).id){
                postDate = 7
                homeViewModel.getJobLIst(postDate,customView.findViewById<EditText>(R.id.keyWord).text.toString(),customView.findViewById<EditText>(R.id.location).text.toString(),SessionManager.getInstance(requireContext()).getUserId)
                alert_dialog?.dismiss()

            }else  if(checkedId == customView.findViewById<RadioButton>(R.id.last30Hour).id){
                postDate  = 30
                homeViewModel.getJobLIst(0,customView.findViewById<EditText>(R.id.keyWord).text.toString(),customView.findViewById<EditText>(R.id.location).text.toString(),SessionManager.getInstance(requireContext()).getUserId)
                alert_dialog?.dismiss()

            }else{
                postDate  = 30
                homeViewModel.getJobLIst(0,customView.findViewById<EditText>(R.id.keyWord).text.toString(),customView.findViewById<EditText>(R.id.location).text.toString(),SessionManager.getInstance(requireContext()).getUserId)
                alert_dialog?.dismiss()

            }

            // on below line we are displaying a toast message.
        }
        cvApplyFilter.setOnClickListener {
            binding.progressBar.visibility=View.VISIBLE

            alert_dialog?.dismiss()

          // on below line we are adding check
          // change listener for our radio group.


        }




        alert_dialog.show()

    }


}