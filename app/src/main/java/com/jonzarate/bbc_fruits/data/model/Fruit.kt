package com.jonzarate.bbc_fruits.data.model

import java.io.Serializable

data class Fruit (
    val type: String,
    val price: Int,
    val weight: Int
) : Serializable