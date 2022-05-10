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
    fun register( @PartMap data : LinkedHashMap<String, RequestBody>,
                 @Part image: MultipartBody.Part): Call<User>

    @Multipart
    @POST("stade/")
    fun addStade( @PartMap data : LinkedHashMap<String, RequestBody>,
                  @Part image: MultipartBody.Part): Call<Stade>
    @GET("stade/")
    fun getStadeUser():Call<List<Stade>>

    @GET("stade/my")
    //fun getStade(@Header("Authorization") authorization : String):Call<List<Stade>>
    fun getStade():Call<List<Stade>>



    @GET("stade/{id}")
    fun getStadeById(@Path("id") id : String):Call<Stade>

    @PUT("stade/lala/{id}")
    fun addLigueToSatde(@Path("id") id : String,@Body idLigue:String):Call<Stade>

    @GET("stade/pay/{id}")
    fun getLigueStadeById(@Path("id") id : String):Call<Stade>


    @GET("ligue/")
    fun getLigues():Call<List<Ligue>>

    @GET("ligue/{id}")
    fun getLigueById(@Path("id") id : String):Call<Ligue>

    @Multipart
    @POST("ligue/")
    fun addLigue( @PartMap data : LinkedHashMap<String, RequestBody>,
                   @Part image: MultipartBody.Part): Call<Ligue>


    @GET("equipe/")
    fun getEquipes():Call<List<Equipe>>

    @GET("equipe/{id}")
    fun getEquipeById(@Path("id") id : String):Call<Equipe>

    @Multipart
    @POST("equipe/")
    fun addequipe( @PartMap data : LinkedHashMap<String, RequestBody>,
                  @Part image: MultipartBody.Part): Call<Equipe>

    @PUT("user/profile")
    fun updateProfile(@Body user :User):Call<User>

    @GET("user/{id}")
    fun getProfile(@Path("id") id : String):Call<User>

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