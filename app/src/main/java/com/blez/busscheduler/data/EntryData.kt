package com.blez.busscheduler.data

import com.google.gson.annotations.SerializedName

data class EntryData(
    val token: String,
    val BusNumber: String,
    val Destination: String,
    val Depature: String,
    val Stoppage: List<String>
)
data class EntryDataSTATUS(
    @SerializedName("message")
    val message:String
)







