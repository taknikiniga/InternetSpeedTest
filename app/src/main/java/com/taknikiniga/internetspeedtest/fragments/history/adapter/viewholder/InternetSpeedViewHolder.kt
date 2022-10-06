package com.taknikiniga.internetspeedtest.fragments.history.adapter.viewholder

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.taknikiniga.internetspeedtest.databinding.HistoryRcvItemBinding
import com.taknikiniga.internetspeedtest.fragments.history.model.InternetSpeedModel

sealed class InternetSpeedViewHolder(binding:View) : RecyclerView.ViewHolder(binding){

    class InternetSpeedHistoryVH(private val binding:HistoryRcvItemBinding):InternetSpeedViewHolder(binding.root){
        fun bind(item:InternetSpeedModel.InternetHistoryModel){
            binding.historyData = item
        }
    }
}