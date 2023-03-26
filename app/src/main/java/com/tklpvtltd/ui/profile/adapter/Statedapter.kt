package com.tklpvtltd.ui.profile.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.data.responseModel.State
import com.tklpvtltd.R
import kotlin.collections.ArrayList



class Statedapter(val callBack: CountryCallBack,context: Context, private val data: List<State>) :
    ArrayAdapter<State>(context, 0, data) {

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
        fun getCountryCall(position: Int,list:kotlin.collections.List<State>)
    }
}