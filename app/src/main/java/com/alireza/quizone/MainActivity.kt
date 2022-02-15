package com.alireza.quizone

import android.app.ActivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.alireza.quizone.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var navigition: NavController
    private lateinit var buttomNav: BottomNavigationView
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)


    val navHostFragment = supportFragmentManager
        .findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment

    navigition = navHostFragment.navController
    appBarConfiguration = AppBarConfiguration(navigition.graph)


    buttomNav = findViewById(R.id.bottomNavigationView)
    NavigationUI.setupWithNavController(buttomNav, navigition)


    buttomNav.setOnItemSelectedListener() {
        when (it.itemId) {
            R.id.miGlide -> {
                navigition.popBackStack()
                navigition.navigate(R.id.glide)
            }
            R.id.miExecuter -> {
                navigition.navigate(R.id.excuter)
            }
            R.id.miEnqueue -> {
                navigition.popBackStack()
                navigition.navigate (R.id.enqueue)
            }
        }
        true
    }
}

override fun onBackPressed() {
    super.onBackPressed()
    finish()
}


}