package com.taknikiniga.internetspeedtest.main.mainviewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taknikiniga.internetspeedtest.connection_monitor.ListenConnectivity
import com.taknikiniga.internetspeedtest.custom_dialog.IntCustomDialog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val listenConnectivity: ListenConnectivity,

) : ViewModel() {

    fun checkInternet(context: Context) = viewModelScope.launch {
        val intCustomDialog = IntCustomDialog(context)
        listenConnectivity.isConnected.collectLatest {
            when (it) {
                false -> {
                    intCustomDialog.showInternetDialog()
                    CoroutineScope(Dispatchers.Main).launch {
                        Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show()
                    }
                }
                true -> {
                    intCustomDialog.dismissInternetDialog()
                    Toast.makeText(context, "Internet Connection ON", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}