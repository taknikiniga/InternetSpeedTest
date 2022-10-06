package com.taknikiniga.internetspeedtest.fragments.history.history_viewmodel

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.taknikiniga.internetspeedtest.databinding.RecyclerViewLytBinding
import com.taknikiniga.internetspeedtest.fragments.history.adapter.InternetSpeedAdapter
import com.taknikiniga.internetspeedtest.fragments.history.model.InternetSpeedModel
import com.taknikiniga.internetspeedtest.localdatabase.LocalDbDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(private val dao: LocalDbDao) : ViewModel() {

    val historyData = mutableListOf<InternetSpeedModel>()

    var getRecycler:MutableLiveData<View> = MutableLiveData()
    val _getRcvLiveData:LiveData<View>
        get() =getRecycler

    fun getInternetHistory(context:Context)= viewModelScope.launch{
        val adapter = InternetSpeedAdapter()
        var recyclerView:RecyclerViewLytBinding =RecyclerViewLytBinding.inflate(LayoutInflater.from(context),null,false)

        dao.getInternetHistory().collectLatest {
            if (it.isEmpty())else{
                historyData.clear()
                for (i in it){
                    historyData.add(
                        InternetSpeedModel.InternetHistoryModel(
                            date = 1000,
                            download = i.download,
                            uploadSpeed = i.uploadSpeed,
                            ping = i.ping
                        )
                    )

                }
                adapter.submitList(historyData)
                recyclerView?.rcv?.adapter = adapter
                recyclerView?.rcv?.layoutManager = LinearLayoutManager(context)
                adapter.notifyDataSetChanged()
                getRecycler.value =recyclerView.root
            }
        }

    }

}