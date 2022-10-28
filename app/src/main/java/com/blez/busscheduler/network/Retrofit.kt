package com.blez.busscheduler.network

import com.blez.busscheduler.utils.Contants.BASE_URL
import com.google.gson.GsonBuilder
import retrofit2.converter.gson.GsonConverterFactory

class Retrofit {
    companion object{
        fun getRetrofitInstance() : retrofit2.Retrofit
        {
            return retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        }
    }
}