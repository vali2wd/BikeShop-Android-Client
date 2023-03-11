package com.example.bikeshopclient

data class ShopResponse(
    val count: Int,
    val data: List<Data>,
    val pageIndex: Int,
    val pageSize: Int
)