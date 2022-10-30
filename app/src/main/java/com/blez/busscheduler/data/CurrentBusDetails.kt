package com.blez.busscheduler.data

data class CurrentBusDetails(
    val BusNumber: String,
    val Depature: String,
    val Destination: String,
    val Stoppage: List<String>
)
data class CurrentBusDetailsDelete(
    val token : String,
    val BusNumber: String,
    val Depature: String,
    val Destination: String,
    val Stoppage: List<String>
)
