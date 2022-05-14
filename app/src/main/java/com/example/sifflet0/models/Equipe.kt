package com.example.sifflet0.models

data class Equipe(
    val _id: String? = null,
    val appar: Boolean? = null,
    val createdAt: String? = null,
    val discription: String? = null,
    val image: String? = null,
    val joueurs_id: List<Joueur>? = null,
    val lose: Int? = null,
    val nbJ: Int? = null,
    val nom: String? = null,
    val `null`: Int? = null,
    val point: Int? = null,
    val updatedAt: String? = null,
    val user: String? = null,
    val win: Int? = null
)