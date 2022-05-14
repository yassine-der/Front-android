package com.example.sifflet0.api

import android.content.Context
import android.content.SharedPreferences
import com.example.sifflet0.models.*
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

    @GET("joueur/{id}")
    fun getJoueurById(@Path("id") id : String):Call<Joueur>

    @PUT("stade/lala/{id}/{ideologue}")
    fun addLigueToSatde(@Path("id") id : String,@Path("ideologue") idligue : String):Call<Stade>

    @PUT("ligue/{id}/{idequipe}")
    fun addEquipeToLigue(@Path("id") id : String,@Path("idequipe") idligue : String):Call<Ligue>

    @PUT("equipe/{id}/{joueurid}")
    fun addJoueurToEquipe(@Path("id") id : String,@Path("joueurid") idligue : String):Call<Equipe>

    @GET("stade/pay/{id}")
    fun getLigueStadeById(@Path("id") id : String):Call<Stade>

    @GET("ligue/pay/{id}")
    fun getEquipeLigueById(@Path("id") id : String):Call<LigueResponse>

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

    @Multipart
    @POST("joueur/")
    fun addjoueur( @PartMap data : LinkedHashMap<String, RequestBody>,
                  @Part image: MultipartBody.Part): Call<Joueur>

    @PUT("user/profile")
    fun updateProfile(@Body user :User):Call<User>

    @GET("user/{id}")
    fun getProfile(@Path("id") id : String):Call<User>

    @GET("joueur/")
    fun getJoueur():Call<List<Joueur>>

    @GET("joueur/my")
    fun getMyJoueur():Call<List<Joueur>>


    @GET("Equipe/{id}")
    fun getJoueurOfEquipe(@Path("id") id : String):Call<Equipe>

}