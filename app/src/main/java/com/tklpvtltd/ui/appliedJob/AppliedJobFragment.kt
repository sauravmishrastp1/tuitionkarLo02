package com.tklpvtltd.ui.appliedJob

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.data.responseModel.JobList
import com.tklpvtltd.LoginActivity
import com.tklpvtltd.MainViewModel
import com.tklpvtltd.R
import com.tklpvtltd.databinding.FragmentAppliedJobBinding
import com.tklpvtltd.databinding.FragmentHomeBinding
import com.tklpvtltd.ui.ActivityJobDetails
import com.tklpvtltd.ui.adapter.JobListAdapter
import com.tklpvtltd.utils.prefrence.SessionManager
import org.koin.androidx.viewmodel.ext.android.viewModel

class AppliedJobFragment : Fragment() {


    private val mainViewModel by viewModel<MainViewModel>()

    private var _binding: FragmentAppliedJobBinding? = null
    private lateinit var jobListAdapter: JobListAdapter

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAppliedJobBinding.inflate(inflater, container, false)
        val root: View = binding.root
        if(SessionManager.getInstance(requireContext()).isLogin){
            mainViewModel.getAppliedJobs(SessionManager.getInstance(requireContext()).getUserId)

        }else{
            showAlert("Please Login","Error")
        }
        jobListAdapter = JobListAdapter(object :JobListAdapter.CallBackJobList{
            override fun getJobData(list: MutableList<JobList>, position: Int) {
                startActivity(Intent(requireContext(), ActivityJobDetails::class.java).apply {
                    putExtra("jobId",list[position].id)
                    putExtra("isApplied",true)

                })
            }

        })
        observer()
        return root
    }

    override fun onResume() {
        super.onResume()

    }

    fun observer(){
        mainViewModel.appliedJobsResponse.observe(viewLifecycleOwner){
            jobListAdapter.setData(it.body()!!.data.jobList as MutableList<JobList>)
            binding.rvJobList.adapter = jobListAdapter
        }
    }

    fun showAlert(message: String,title:String) {
        val alertDialog = AlertDialog.Builder(requireContext()).create()
        alertDialog.setTitle(title)
        alertDialog.setCancelable(false)
        alertDialog.setMessage(message)
        alertDialog.setButton(
            AlertDialog.BUTTON_POSITIVE, "OK"
        ) { dialog, which -> dialog.dismiss()
            startActivity(Intent(requireContext(), LoginActivity::class.java))
        }
        alertDialog.show()

    }

}