package com.example.workmanager.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.example.workmanager.R
import com.example.workmanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
private lateinit var mainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding=ActivityMainBinding.inflate(layoutInflater)
        val navHostFragment=supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        navHostFragment.navController
       return setContentView(mainBinding.root)
    }
}