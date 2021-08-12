package com.adarsh.gitclosedpullrequest.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.gitclosedpullrequest.databinding.ActivityClosedPullListingBinding

class ClosedPullListingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityClosedPullListingBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClosedPullListingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = (supportFragmentManager.findFragmentById(binding.navHostFragment.id) as NavHostFragment).navController
    }

    override fun onNavigateUp(): Boolean = navController.navigateUp()
}