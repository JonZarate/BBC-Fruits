package com.jonzarate.bbc_fruits.data.net

import com.jonzarate.bbc_fruits.data.model.FruitResponse
import retrofit2.Call
import retrofit2.http.GET

interface FruitsApi {

    @GET("data.json")
    fun getFruits() : Call<FruitResponse>
}