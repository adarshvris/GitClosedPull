package com.adarsh.gitclosedpullrequest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.gitclosedpullrequest.databinding.ActivityClosedPullListingBinding

class ClosedPullListingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityClosedPullListingBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClosedPullListingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = Navigation.findNavController(binding.navHostFragment)
    }

    override fun onNavigateUp(): Boolean = navController.navigateUp()
}