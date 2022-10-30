package com.blez.busscheduler.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.blez.busscheduler.data.CurrentBusDetails
import com.blez.busscheduler.data.Data
import com.blez.busscheduler.databinding.CurrentScheduleViewBinding

class CurrentScheduleAdapter(val context : Context,val currentBusDetails: List<Data>) : RecyclerView.Adapter<CurrentScheduleAdapter.ItemView>() {
    private lateinit var binding: CurrentScheduleViewBinding
    inner class ItemView(binding: CurrentScheduleViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemView {
        binding = CurrentScheduleViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ItemView(binding)
    }

    override fun onBindViewHolder(holder: ItemView, position: Int) {

        val text =  ArrayList<String>()
        currentBusDetails[position].Stoppage.forEach { text.add(it) }

        binding.busNoText.text = currentBusDetails[position].BusNumber
        binding.atText.text = currentBusDetails[position].Destination

        binding.depatureText.text = currentBusDetails[position].Depature
        binding.stoppageText.text = text.toString()
    }

    override fun getItemCount(): Int {
        return currentBusDetails.size
    }


}