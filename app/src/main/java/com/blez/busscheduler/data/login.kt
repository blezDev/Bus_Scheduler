package com.blez.busscheduler.data

import com.google.gson.annotations.SerializedName

data class login(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)
data class LoginStatus(
    @SerializedName("message")
    val message : String,
    @SerializedName("token")
    val token : String,
    @SerializedName("usrtype")
    val usrtype : String

)