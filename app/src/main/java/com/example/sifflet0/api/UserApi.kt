package com.example.sifflet0.api

import android.content.Context
import android.content.SharedPreferences
import com.example.sifflet0.models.Equipe
import com.example.sifflet0.models.Ligue
import com.example.sifflet0.models.Stade
import com.example.sifflet0.models.User
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface UserApi {

    @POST("user/login")
    fun seConnecter(@Body user:User): Call<User>

    @Multipart
    @POST("user/")
    //fun register(@Body user:User,@Part): Call<User>
    fun register(        @PartMap data : LinkedHashMap<String, RequestBody>,
                 @Part image: MultipartBody.Part

    ): Call<User>


    @GET("stade/")
    //fun getStade(@Header("Authorization") authorization : String):Call<List<Stade>>
    fun getStade():Call<List<Stade>>

    @GET("ligue/")
    fun getLigues():Call<List<Ligue>>

    @GET("equipe/")
    fun getEquipes():Call<List<Equipe>>
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