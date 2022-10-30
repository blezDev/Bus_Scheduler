package com.blez.busscheduler.data

data class ScheduleList(
    val `data`: List<Data>,
    val message: String
)

data class Data(
    val BusNumber: String,
    val Depature: String,
    val Destination: String,
    val Stoppage: List<String>,
    val __v: Int,
    val _id: String
)