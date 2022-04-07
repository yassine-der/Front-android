package com.example.sifflet0.api

import com.example.sifflet0.models.Stade
import com.example.sifflet0.models.User
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

import retrofit2.http.Query

interface UserApi {
    @POST("user/login")
    fun seConnecter(@Body user:User): Call<User>

    @GET("stade/")
    fun getStade():Call<List<Stade>>
/*
    companion object {

         val  BASE_URL = "http://192.168.56.1:3000/"

        fun create() : UserApi {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(UserApi::class.java)
        }
    }
    */

}