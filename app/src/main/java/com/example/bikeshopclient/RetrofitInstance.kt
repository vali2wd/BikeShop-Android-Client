package com.example.bikeshopclient

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: BikeShopApi by lazy{
        Retrofit.Builder()
            .baseUrl("http://192.168.100.46:5000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BikeShopApi::class.java)
    }
}