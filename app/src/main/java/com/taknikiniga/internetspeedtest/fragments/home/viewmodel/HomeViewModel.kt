package com.taknikiniga.internetspeedtest.fragments.home.viewmodel

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.taknikiniga.internetspeedtest.R
import com.taknikiniga.internetspeedtest.databinding.HomeItemLytBinding
import com.taknikiniga.internetspeedtest.fragments.history.model.InternetSpeedModel
import com.taknikiniga.internetspeedtest.localdatabase.LocalDbDao
import com.taknikiniga.internetspeedtest.localdatabase.model.InternetHistoryModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val connectivityManager: ConnectivityManager,
    private val localDbDao: LocalDbDao
) : ViewModel() {


    @RequiresApi(Build.VERSION_CODES.M)
    fun getDownloadSpeed() =
        connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)?.linkDownstreamBandwidthKbps?.div(
            1000
        )

    @RequiresApi(Build.VERSION_CODES.M)
    fun getUploadSpeed() =
        connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)?.linkUpstreamBandwidthKbps?.div(
            1000
        )

    @RequiresApi(Build.VERSION_CODES.M)
    fun setDownloadSpeed(context: Context): Flow<View> = flow {
        val downloadLyt: HomeItemLytBinding =
            HomeItemLytBinding.inflate(LayoutInflater.from(context))
        downloadLyt.speedInfo.text = "${getDownloadSpeed()}"
        emit(downloadLyt.root)
    }.flowOn(Dispatchers.Main)

    @RequiresApi(Build.VERSION_CODES.M)
    fun setUploadSpeed(context: Context): Flow<View> = flow {
        val downloadLyt: HomeItemLytBinding =
            HomeItemLytBinding.inflate(LayoutInflater.from(context))
        downloadLyt.speedLabel.text = "Upload"
        downloadLyt.speedImg.setImageResource(R.drawable.ic_baseline_cloud_upload_24)
        downloadLyt.speedInfo.text = "${getUploadSpeed()}"
        emit(downloadLyt.root)
    }.flowOn(Dispatchers.Main)

    @RequiresApi(Build.VERSION_CODES.M)
    suspend fun insertInternetData() {
        localDbDao.insertInternetHistory(
            internetHistoryModel = InternetHistoryModel(
                Date(),
                download = "${getDownloadSpeed()}",
                uploadSpeed = "${getUploadSpeed()}",
                ping = "no data"
            )
        )
    }

}