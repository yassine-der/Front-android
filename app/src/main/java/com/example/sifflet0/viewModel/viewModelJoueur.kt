package com.example.sifflet0.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sifflet0.api.RetrofiteInstance
import com.example.sifflet0.models.Equipe
import com.example.sifflet0.models.Joueur
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class viewModelJoueur: ViewModel()  {
    lateinit var lifeDataList: MutableLiveData<List<Joueur>>
    init {
        lifeDataList = MutableLiveData()

    }
    fun getLiveDataObserver(): MutableLiveData<List<Joueur>> {
        return lifeDataList
    }
    fun makeApiCall89(context: Context?) {
        val apiInterface = RetrofiteInstance.api(context)
        apiInterface.getJoueur().enqueue(object : Callback<List<Joueur>> {
            override fun onFailure(call: Call<List<Joueur>>, t: Throwable) {
                lifeDataList.postValue(null)
            }

            override fun onResponse(call: Call<List<Joueur>>, response: Response<List<Joueur>>) {
                lifeDataList.postValue(response.body())
            }


        })
    }


}