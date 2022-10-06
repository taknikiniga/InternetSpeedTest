package com.taknikiniga.internetspeedtest.localdatabase.viewmodel

import androidx.lifecycle.ViewModel
import com.taknikiniga.internetspeedtest.localdatabase.LocalDbDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocalViewModel @Inject constructor(private val localDbDao: LocalDbDao) : ViewModel() {



}