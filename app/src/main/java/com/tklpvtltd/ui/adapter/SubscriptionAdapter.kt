package com.tklpvtltd.ui.adapter

import android.graphics.Color
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.data.responseModel.JobList
import com.data.responseModel.OurPlan
import com.tklpvtltd.R
import com.tklpvtltd.databinding.LayouSuscribtionPlanItemBinding


class SubscriptionAdapter(val callBackBuy: CallBackBuy) : RecyclerView.Adapter<SubscriptionAdapter.WalletViewHolder>() {
    private var list = mutableListOf<OurPlan>()
    private var note =""
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalletViewHolder {
        val binding =
            LayouSuscribtionPlanItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WalletViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WalletViewHolder, position: Int) {
         holder.setData(position)
    }
    fun setData(jobList: MutableList<OurPlan>,note:String){
        this.note = note
        this.list = jobList
    }


    override fun getItemCount(): Int {
        return list.size
    }

    inner class WalletViewHolder(val binding: LayouSuscribtionPlanItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(position: Int) {
           binding.tvPlanName.text = list[position].name
           binding.tvPrice.text = list[position].price+"/Year"
            binding.tvPurchase.text = list[position].planHistory
            if(list[position].planHistory=="Active Plan"){
                binding.tvPlanActiveDuration.text = note
            }
//           binding.content1.text = list[position].content
//            binding.content1.text = Html.fromHtml(Html.fromHtml(list[position].content).toString())
            binding.weview.loadData(list[position].content,"","");
            binding.weview.settings.javaScriptEnabled = true
            binding.weview.setBackgroundColor(Color.TRANSPARENT)
            binding.root.setOnClickListener {
                callBackBuy.getJobData(list,position)
            }
        }


    }
    interface CallBackBuy{
        fun getJobData(list: MutableList<OurPlan>,position: Int)
    }

}