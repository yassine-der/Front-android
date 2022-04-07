package com.example.sifflet0.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofiteInstance {
    var BASE_URL = "http://192.168.80.1:3000/"
    val api :UserApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            //.baseUrl(UserApi.BASE_URL)
            .build()
            .create(UserApi::class.java)
    }
}