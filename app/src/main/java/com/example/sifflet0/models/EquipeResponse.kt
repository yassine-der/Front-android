package com.example.sifflet0.models

data class LigueResponse (
    val _id: String? = null,
    val createdAt: String? = null,
    val discription: String? = null,
    val equipes_ids: List<EquipeVraiResponse>? = null,
    val image: String? = null,
    val matchs_ids: List<Any>? = null,
    val nom: String? = null,
    val updatedAt: String? = null,
    val user: String? = null

)