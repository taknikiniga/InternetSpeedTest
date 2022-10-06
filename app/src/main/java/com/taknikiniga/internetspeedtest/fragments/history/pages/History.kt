package com.taknikiniga.internetspeedtest.fragments.history.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.taknikiniga.internetspeedtest.R
import com.taknikiniga.internetspeedtest.databinding.FragmentHistoryBinding
import com.taknikiniga.internetspeedtest.fragments.history.history_viewmodel.HistoryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_history.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@AndroidEntryPoint
class History : Fragment() {

    private var binding:FragmentHistoryBinding?=null
    val historyVm : HistoryViewModel by viewModels()

    val mainScope = CoroutineScope(Dispatchers.Main)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = inflate(inflater,R.layout.fragment_history, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainScope.launch {
            historyVm?.getInternetHistory(requireContext())
        }

        mainScope.launch {
            historyVm._getRcvLiveData.observe(viewLifecycleOwner){
                if (it.parent != null) {
                    (it.parent as ViewGroup).removeView(it) // <- fix
                }
                binding?.mainView?.addView(it)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}