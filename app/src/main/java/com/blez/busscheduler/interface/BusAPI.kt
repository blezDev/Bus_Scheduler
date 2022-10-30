package com.blez.busscheduler.`interface`

import com.blez.busscheduler.data.*
import retrofit2.http.Body
import retrofit2.http.POST

interface BusAPI {
    @POST("/register")
    fun registerUser(@Body info: Register): retrofit2.Call<RegisterStatus>
    @POST("/login")
    fun login(@Body info: login) : retrofit2.Call<LoginStatus>
    @POST("/AddSchedule")
    fun addSchedule(@Body info : EntryData) : retrofit2.Call<EntryDataSTATUS>
    @POST("/ShowSchedule")
     fun showSchedule(@Body auth : Auth) : retrofit2.Call<ScheduleList>
     @POST("/DeleteSchedule")
     fun deleteSchedule(@Body info : CurrentBusDetailsDelete) : retrofit2.Call<RegisterStatus>


}
