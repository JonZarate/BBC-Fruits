package com.jonzarate.bbc_fruits.data.repo

import com.jonzarate.bbc_fruits.data.model.FruitResponse
import com.jonzarate.bbc_fruits.data.net.FruitsApi

class FruitRepository (
    private val api: FruitsApi,
    private val app: AppRepository
) {

    suspend fun getFruits() : FruitResponse? {
        val call = api.getFruits()
        return app.executeCall(call)
    }

}