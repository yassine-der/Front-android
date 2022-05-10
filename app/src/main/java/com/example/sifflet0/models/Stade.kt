package com.example.sifflet0.models

import com.google.gson.annotations.SerializedName

data class Stade(
    val _id: String? = null,
    val createdAt: String? = null,
    var discription: String? = null,
    val image: String? = null,
    val lat: String? = null,
    var ligues_id: List<Ligue>? = null,
    val lon: String? = null,
    var nom: String? = null,
    var num: String? = null,
    val taxPrice: Int? = null,
    val updatedAt: String? = null,
    val user: String? = null
)
