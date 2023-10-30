package com.carlos.stockmarket.data.remote

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface StockApi {

    @GET("query?function=LISTING_STATUS")
    suspend fun getListings(@Query("apikey") apiKey: String): ResponseBody

    companion object {
        const val API_KEY = "8M7UYJE987ZL5R58"
        const val BASE_URL = "https://www.alphavantage.co"
    }
}