package com.tklpvtltd.ui.profile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.data.responseModel.JobList
import com.data.remote.baseApi.DataRepository.Companion.BASE_URL
import com.data.remote.baseApi.DataRepository.Companion.IMAGE_BASE_URL
import com.data.responseModel.City
import com.data.responseModel.State
import com.tklpvtltd.R
import com.tklpvtltd.databinding.JobsListBinding
import com.tklpvtltd.databinding.LayoutSingleItemBinding
import java.util.*
import kotlin.collections.ArrayList

class AlertStateAdapter(val callBack:CallBackJobList) : RecyclerView.Adapter<AlertStateAdapter.WalletViewHolder>() {
    private var list = mutableListOf<State>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalletViewHolder {
        val binding =
            LayoutSingleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WalletViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WalletViewHolder, position: Int) {
         holder.setData(position)
    }

    fun setData(jobList: MutableList<State>){
        this.list = jobList
        notifyDataSetChanged()

    }


    override fun getItemCount(): Int {
        return list.size
    }

    inner class WalletViewHolder(val binding: LayoutSingleItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun setData(position: Int){
                binding.tvName.text = list[position].name
                binding.root.setOnClickListener {
                    callBack.getJobData(list,position)
                }

            }

    }
    interface CallBackJobList{
        fun getJobData(list: MutableList<State>,position: Int)
    }

    fun searchResult(keyWord:String):MutableList<State>{
         var list_ = mutableListOf<State>()

        for(i in list){
            var name = i.name
            if(name.lowercase(Locale.getDefault())!!.contains(keyWord)){
                list_.add(i)
            }
        }
        return list_

    }

}