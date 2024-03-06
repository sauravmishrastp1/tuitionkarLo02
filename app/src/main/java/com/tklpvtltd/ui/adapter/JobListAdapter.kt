package com.tklpvtltd.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.data.responseModel.JobList
import com.data.remote.baseApi.DataRepository.Companion.BASE_URL
import com.data.remote.baseApi.DataRepository.Companion.IMAGE_BASE_URL
import com.tklpvtltd.R
import com.tklpvtltd.databinding.JobsListBinding
import java.util.*
import kotlin.collections.ArrayList

class JobListAdapter(val callBack:CallBackJobList) : RecyclerView.Adapter<JobListAdapter.WalletViewHolder>() {
    private var list = mutableListOf<JobList>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalletViewHolder {
        val binding =
            JobsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WalletViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WalletViewHolder, position: Int) {
         holder.setData(position)
    }
    fun setData(jobList: MutableList<JobList>){
        this.list = jobList
        notifyDataSetChanged()

    }


    override fun getItemCount(): Int {
        return list.size
    }

    inner class WalletViewHolder(val binding: JobsListBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun setData(position: Int){
                binding.title.text = list[position].designation
//                binding.tvLocation.text = list[position].cityName.replace("[","").replace("]","")
                binding.tvSlary.text = "₹${list[position].salary}/Month"
                Glide.with(binding.root.context).load("${IMAGE_BASE_URL}${list[position].logo}").placeholder(
                    R.drawable.tkllogo).circleCrop().into(binding.imgJobLogo)
                binding.jobDetail.setOnClickListener {
                    callBack.getJobData(list,position)
                }

            }

    }
    interface CallBackJobList{
        fun getJobData(list: MutableList<JobList>,position: Int)
    }

    fun searchResult(keyWord:String):MutableList<JobList>{
         var list = mutableListOf<JobList>()

        for(i in list){
            if(i.cityName.lowercase(Locale.getDefault())!!.contains(keyWord) || i.designation.lowercase(Locale.getDefault())!!.contains(keyWord) || i.jobLocation.lowercase(Locale.getDefault())!!.contains(keyWord)){
                list.add(JobList(i.cityName,i.companyName,i.companyPhone,i.createdAt,i.deletedAt,i.description,i.designation,i.experienceNeeded,i.id,i.instituteName,i.isActive,i.isApproved,i.isDeleted,i.jobCategory,i.jobLocation.toString(),i.logo.toString(),i.processOfSelections,i.qualificationNeeded,i.requirement,i.salary,i.updatedAt))
            }
        }
        return list

    }

}