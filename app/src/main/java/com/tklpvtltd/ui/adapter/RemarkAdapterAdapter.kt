package com.tklpvtltd.ui.adapter

import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.data.responseModel.JobList
import com.data.remote.baseApi.DataRepository.Companion.BASE_URL
import com.data.remote.baseApi.DataRepository.Companion.IMAGE_BASE_URL
import com.data.responseModel.RemarkListing
import com.tklpvtltd.R
import com.tklpvtltd.databinding.JobsListBinding
import com.tklpvtltd.databinding.LayoutRemarkBinding
import java.util.*
import kotlin.collections.ArrayList

class RemarkAdapterAdapter : RecyclerView.Adapter<RemarkAdapterAdapter.WalletViewHolder>() {
    private var remarkListing = mutableListOf<RemarkListing>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalletViewHolder {
        val binding =
            LayoutRemarkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WalletViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WalletViewHolder, position: Int) {
        holder.setData(position)
    }

    fun setData(remarkListing: MutableList<RemarkListing>) {
        this.remarkListing = remarkListing
        notifyDataSetChanged()

    }


    override fun getItemCount(): Int {
        return remarkListing.size
    }

    inner class WalletViewHolder(val binding: LayoutRemarkBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(position: Int) {
            binding.weview.text = Html.fromHtml(Html.fromHtml(remarkListing[position].name,).toString());

//            binding.weview.loadData(remarkListing[position].name,"","");
//            binding.weview.settings.javaScriptEnabled = true

        }

    }
}