package com.tklpvtltd.ui.howWeWork

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.tklpvtltd.MainViewModel
import com.tklpvtltd.R
import com.tklpvtltd.databinding.FragmentDashboardBinding
import com.tklpvtltd.utils.prefrence.SessionManager
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardFragment : Fragment() {
     private lateinit var binding: FragmentDashboardBinding
    private val mainViewModel by viewModel<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(layoutInflater,container,false)
        mainViewModel.getJobSeejerProfile(SessionManager.getInstance(requireContext()).getUserId)
        mainViewModel.getCandidateDashboard(SessionManager.getInstance(requireContext()).getUserId)
        observeData()
        binding.cardNotification.setOnClickListener {

        }

        binding.jobAppliedCard.setOnClickListener {
            findNavController().navigate(R.id.appliedJobFragment)

        }
        binding.Totakjobcard.setOnClickListener {
           findNavController().navigate(R.id.appliedJobFragment)
        }

        binding.cardNotification.setOnClickListener {
            findNavController().navigate(R.id.nav_remark)
        }

        return binding.root
    }

    fun observeData(){

        mainViewModel.candidateDashbaordResponse.observe(viewLifecycleOwner){

           try {
               binding.tvUserName.text = it.body()!!.data!!.profile.fullName

               binding.tvTotalJobsCount.text = it.body()!!.tile.totalActiveJob.toString()
               binding.tvTotalAppliedJobsCount.text = it.body()!!.tile.totalJobApplied.toString()
               binding.tvTotalNotificationCount.text = it.body()!!.tile.notifications.toString()
           }catch (e :Exception){

           }

        }


    }


}