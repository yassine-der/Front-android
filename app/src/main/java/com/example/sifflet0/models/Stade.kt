package com.example.sifflet0.models

import com.google.gson.annotations.SerializedName

data class Stade(
    val _id: String? = null,
    val createdAt: String? = null,
    val discription: String? = null,
    val image: String? = null,
    val lat: String? = null,
    val ligues_id: List<Any>? = null,
    val lon: String? = null,
    val nom: String? = null,
    val taxPrice: Int? = null,
    val updatedAt: String? = null,
    val user: String? = null
)
