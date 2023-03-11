package com.example.bikeshopclient

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BikeShopApi {
    @GET("/api/products")
    suspend fun getProducts(): Response<ShopResponse>
}