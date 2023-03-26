package com.tklpvtltd.ui.remark.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.data.responseModel.JobList
import com.data.remote.baseApi.DataRepository.Companion.BASE_URL
import com.data.remote.baseApi.DataRepository.Companion.IMAGE_BASE_URL
import com.data.responseModel.Country
import com.tklpvtltd.R
import com.tklpvtltd.databinding.JobsListBinding
import java.util.*
import kotlin.collections.ArrayList



class CustomAdapter(val callBack: CountryCallBack,context: Context, private val data: List<Country>) :
    ArrayAdapter<Country>(context, 0, data) {

    // Other functions go here

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.layout_single_item, parent, false)
        }

        val item = data[position]
        view!!.findViewById<TextView>(R.id.tvName).text = item.name
          view.setOnClickListener {
              callBack.getCountryCall(position,data)
          }
        return view
    }
    interface CountryCallBack{
        fun getCountryCall(position: Int,list:kotlin.collections.List<Country>)
    }
}