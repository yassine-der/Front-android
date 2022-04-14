package com.example.sifflet0.api

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import com.example.sifflet0.PREF_NAME
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofiteInstance {
    lateinit var  sharedPreferences: SharedPreferences

    var BASE_URL = "http://192.168.80.1:3000/"
/*
    val api :UserApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            //.baseUrl(UserApi.BASE_URL)
            //.client(okhttpClient(context)) // Add our Okhttp client

            .build()
            .create(UserApi::class.java)
    }
*/


    fun api( context: Context?) :UserApi {

        val retrofit = Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okhttpClient(context)) // Add our Okhttp client
            .build()
            //.create(UserApi::class.java)


        return retrofit.create(UserApi::class.java)
    }

    private fun okhttpClient(context: Context?): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(40, TimeUnit.SECONDS)
            .connectTimeout(40, TimeUnit.SECONDS)
            .addInterceptor(AuthInterceptor(context))
            .build()
    }


}