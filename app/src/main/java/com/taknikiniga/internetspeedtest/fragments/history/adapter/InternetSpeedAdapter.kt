package com.taknikiniga.internetspeedtest.fragments.history.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.taknikiniga.internetspeedtest.R
import com.taknikiniga.internetspeedtest.databinding.HistoryRcvItemBinding
import com.taknikiniga.internetspeedtest.fragments.history.adapter.viewholder.InternetSpeedViewHolder
import com.taknikiniga.internetspeedtest.fragments.history.model.InternetSpeedModel
import java.lang.IllegalArgumentException

class InternetSpeedAdapter : ListAdapter<InternetSpeedModel,InternetSpeedViewHolder>(InternetDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InternetSpeedViewHolder {
        return when(viewType){
            R.layout.history_rcv_item -> InternetSpeedViewHolder.InternetSpeedHistoryVH(
                HistoryRcvItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            else -> throw IllegalArgumentException("Invalid View Type")
        }
    }

    override fun onBindViewHolder(holder: InternetSpeedViewHolder, position: Int) {
        when(holder){
            is InternetSpeedViewHolder.InternetSpeedHistoryVH -> holder.bind(getItem(position) as InternetSpeedModel.InternetHistoryModel)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)){
            is InternetSpeedModel.InternetHistoryModel -> R.layout.history_rcv_item
        }
    }
}

class InternetDiffUtil:DiffUtil.ItemCallback<InternetSpeedModel>(){
    override fun areItemsTheSame(
        oldItem: InternetSpeedModel,
        newItem: InternetSpeedModel
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: InternetSpeedModel,
        newItem: InternetSpeedModel
    ): Boolean {
        return oldItem == newItem
    }

}