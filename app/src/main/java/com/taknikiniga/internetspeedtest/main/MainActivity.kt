package com.taknikiniga.internetspeedtest.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.taknikiniga.internetspeedtest.R
import com.taknikiniga.internetspeedtest.databinding.ActivityMainBinding
import com.taknikiniga.internetspeedtest.main.mainviewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController

    val mainVm : MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        /**
         * Checking Internet Connection
         */
        mainVm.checkInternet(this)



        /**
         * Attaching Fragment Container to parent activity
         */

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.findNavController()

        /**
         * Binding Bottom Navigation With Nav Controller
         */

        binding.bottomNavInclude.bottomNav.setupWithNavController(navController)
    }
}