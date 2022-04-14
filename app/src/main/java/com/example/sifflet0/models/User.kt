package com.example.sifflet0.models

data class User(val _id: String ? = null,
                val createdAt: String ? = null,
                var email: String ? = null,
                val image: String ? = null,
                var motdepasse: String ? = null,
                var nom: String ? = null,
                var prenom: String ? = null,
                val updatedAt: String ? = null,
                val verifCode: String ? = null,
                var isProprietaireDestade: Boolean ? = null,
                val token: String ? = null

)


