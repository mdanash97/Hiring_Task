package com.example.hiringtask

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.hiringtask.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.Manifest
import android.view.View
import androidx.core.app.ActivityCompat
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = mainBinding.root
        setContentView(view)

        navController = Navigation.findNavController(this,R.id.nav_host_fragment)
        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        NavigationUI.setupWithNavController(bottomNavigationView,navController,false)
    }
}