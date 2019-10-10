package com.jonzarate.bbc_fruits.data.net

import com.jonzarate.bbc_fruits.data.model.FruitResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface AnalyticsApi {

    @GET("stats?event=load")
    suspend fun sendEventLoad(@Query("data") data: Long)

    @GET("stats?event=display")
    suspend fun sendEventDisplay(@Query("data") data: Long)

    @GET("stats?event=error")
    suspend fun sendEventError(@Query("data") data: String)
}