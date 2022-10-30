package com.blez.busscheduler.data

import com.google.gson.annotations.SerializedName

data class Auth(
    @SerializedName("email")
    val email : String,
    @SerializedName("token")
    val token : String
)
