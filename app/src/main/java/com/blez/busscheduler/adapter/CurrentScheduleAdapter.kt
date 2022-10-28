package com.blez.busscheduler.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.blez.busscheduler.data.CurrentBusDetails
import com.blez.busscheduler.databinding.CurrentScheduleViewBinding

class CurrentScheduleAdapter(val context : Context,val currentBusDetails: List<CurrentBusDetails>) : RecyclerView.Adapter<CurrentScheduleAdapter.ItemView>() {
    private lateinit var binding: CurrentScheduleViewBinding
    inner class ItemView(binding: CurrentScheduleViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemView {
        binding = CurrentScheduleViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ItemView(binding)
    }

    override fun onBindViewHolder(holder: ItemView, position: Int) {
     binding.busNoText.text = { /*TODO()*/}.toString()
     binding.atText.text = { /*TODO()*/}.toString()
     binding.arrivalText.text = { /*TODO()*/}.toString()
     binding.depatureText.text = { /*TODO()*/}.toString()
     binding.stoppageText.text = { /*TODO()*/}.toString()
    }

    override fun getItemCount(): Int {
        return currentBusDetails.size
    }


}