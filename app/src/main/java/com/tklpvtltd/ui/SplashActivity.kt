package com.tklpvtltd.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.tklpvtltd.MainActivity
import com.tklpvtltd.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler().postDelayed({ // This method will be executed once the timer is over
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)


            // close this activity
            finish()
        }, 2000)
    }
}