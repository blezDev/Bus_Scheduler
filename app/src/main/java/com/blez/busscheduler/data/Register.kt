package com.blez.busscheduler.data

import com.google.gson.annotations.SerializedName

data class Register(
    @SerializedName("email")
    val email: String,
    @SerializedName("name")
    val name: String?,
    @SerializedName("password")
    val password: String,
    @SerializedName("number")
    val number: String,
    @SerializedName("usrtype")
    val usrtype: String
)
data class RegisterStatus(
    @SerializedName("message")
    val message:String
)
