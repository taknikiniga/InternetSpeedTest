package com.taknikiniga.internetspeedtest.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.taknikiniga.internetspeedtest.R
import com.taknikiniga.internetspeedtest.databinding.ActivityMainBinding
import com.taknikiniga.internetspeedtest.fragments.home.viewmodel.HomeViewModel
import com.taknikiniga.internetspeedtest.main.mainviewmodel.MainViewModel
import com.taknikiniga.internetspeedtest.services.BoundService
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    val homeVm: HomeViewModel by viewModels()

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

        /**
         * Starting Service
         */

        Intent(this, BoundService::class.java).also {
            startService(it)
        }
        BoundService.DOWNLOAD_SPEED="${homeVm.getDownloadSpeed()}"
        BoundService.UPLOAD_SPEED="${homeVm.getUploadSpeed()}"
    }

}