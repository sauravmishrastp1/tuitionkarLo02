package com.tklpvtltd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tklpvtltd.databinding.ActivityApplyFilterBinding

class ApplyFilter : AppCompatActivity() {
    private lateinit var binding :ActivityApplyFilterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apply_filter)
        binding = ActivityApplyFilterBinding.inflate(layoutInflater)


    }
}