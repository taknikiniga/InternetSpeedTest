package com.taknikiniga.internetspeedtest.custom_dialog

import android.content.Context
import android.view.LayoutInflater
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.taknikiniga.internetspeedtest.R
import com.taknikiniga.internetspeedtest.databinding.InternetAlertDialogeLytBinding
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class IntCustomDialog (private val context: Context) {
    lateinit var binding:InternetAlertDialogeLytBinding
    val dialog =MaterialAlertDialogBuilder(context, R.style.CustomAlertDialog).create()

    fun showInternetDialog() = CoroutineScope(Dispatchers.Main).launch{
        binding = InternetAlertDialogeLytBinding.inflate(LayoutInflater.from(context))
        binding.textView.text = "No Internet Connection"
        binding.lottieAnimationView.setAnimation("no-internet.json")

        dialog.setCanceledOnTouchOutside(false)
        dialog.setView(binding.root)
        dialog.show()
    }

    fun dismissInternetDialog() = CoroutineScope(Dispatchers.Main).launch{
        dialog.dismiss()
    }

}