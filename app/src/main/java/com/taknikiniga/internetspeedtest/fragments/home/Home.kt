package com.taknikiniga.internetspeedtest.fragments.home

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil.inflate
import androidx.fragment.app.viewModels
import com.taknikiniga.internetspeedtest.R
import com.taknikiniga.internetspeedtest.databinding.FragmentHomeBinding
import com.taknikiniga.internetspeedtest.fragments.home.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Home : Fragment() {

    private var binding:FragmentHomeBinding?=null
    val homeVm:HomeViewModel by viewModels()
    val mainScope = CoroutineScope(Dispatchers.Main)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = inflate(inflater, R.layout.fragment_home, container, false)
        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
         * Inserting Current Internet Record
         */
        mainScope.launch {
            homeVm.insertInternetData()
        }

        mainScope.launch {
            homeVm.setDownloadSpeed(requireContext()).collectLatest {
                binding?.homeMain?.addView(it)
            }
        }

        mainScope.launch {
            homeVm.setUploadSpeed(requireContext()).collectLatest {
                binding?.homeMain?.addView(it)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding=null
    }


}