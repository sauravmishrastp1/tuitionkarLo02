package com.tklpvtltd

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.MenuItem.OnMenuItemClickListener
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.tklpvtltd.databinding.ActivityMainBinding
import com.tklpvtltd.utils.prefrence.SessionManager


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)


        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_profile, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        hideItem(SessionManager.getInstance(applicationContext).isLogin)
        val nav_Menu: Menu =  binding.navView.getMenu()
        nav_Menu.findItem(R.id.logout).setOnMenuItemClickListener(object :OnMenuItemClickListener{
            override fun onMenuItemClick(item: MenuItem): Boolean {

                showAlert("Are you sure want to logout?","Logout")
              return true
            }

        })

        nav_Menu.findItem(R.id.login).setOnMenuItemClickListener(object :OnMenuItemClickListener{
            override fun onMenuItemClick(item: MenuItem): Boolean {
                startActivity(Intent(applicationContext,LoginActivity::class.java))

                return true
            }

        })
        if(intent.extras!==null){
            if(intent.getStringExtra("type")=="Paytment"){
                navController.navigate(R.id.nav_slideshow)
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun hideItem(isLogin:Boolean) {
        if(isLogin){
            val nav_Menu: Menu =  binding.navView.getMenu()
            nav_Menu.findItem(R.id.nav_home).isVisible = true
            nav_Menu.findItem(R.id.nav_dashboard).isVisible = true
            nav_Menu.findItem(R.id.nav_profile).isVisible = true
            nav_Menu.findItem(R.id.nav_slideshow).isVisible = true
            nav_Menu.findItem(R.id.nav_remark).isVisible = true
            nav_Menu.findItem(R.id.appliedJobFragment).isVisible = true
            nav_Menu.findItem(R.id.logout).isVisible = true
            nav_Menu.findItem(R.id.login).isVisible = false
        }else{
            val nav_Menu: Menu =  binding.navView.getMenu()
            nav_Menu.findItem(R.id.nav_home).isVisible = true
            nav_Menu.findItem(R.id.nav_dashboard).isVisible = false
            nav_Menu.findItem(R.id.nav_profile).isVisible = false
            nav_Menu.findItem(R.id.nav_slideshow).isVisible = false
            nav_Menu.findItem(R.id.nav_remark).isVisible = false
            nav_Menu.findItem(R.id.appliedJobFragment).isVisible = false
            nav_Menu.findItem(R.id.logout).isVisible = false
            nav_Menu.findItem(R.id.login).isVisible = true
        }

    }

    fun showAlert(message: String,title:String) {
        val alertDialog = AlertDialog.Builder(this).create()
        alertDialog.setTitle(title)
        alertDialog.setMessage(message)
        alertDialog.setButton(
            AlertDialog.BUTTON_POSITIVE, "Logout"
        ) { dialog, which -> dialog.dismiss()
            SessionManager.getInstance(applicationContext).setIsLogin(false)
            SessionManager.getInstance(applicationContext).clear()
            hideItem(false)
            startActivity(Intent(applicationContext,MainActivity::class.java))
            }

        alertDialog.setButton(
            AlertDialog.BUTTON_NEGATIVE, "Cancel"
        ) { dialog, which -> dialog.dismiss()

        }
        alertDialog.show()

    }
}