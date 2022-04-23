package com.example.sifflet0.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sifflet0.api.RetrofiteInstance
import com.example.sifflet0.models.Equipe
import com.example.sifflet0.models.Ligue
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class viewModelEquipe : ViewModel() {
    lateinit var lifeDataList: MutableLiveData<List<Equipe>>
    init {
        lifeDataList = MutableLiveData()

    }
    fun getLiveDataObserver(): MutableLiveData<List<Equipe>> {
        return lifeDataList
    }
    fun makeApiCall2(context: Context?){
        val apiInterface = RetrofiteInstance.api(context)
        apiInterface.getEquipes().enqueue(object : Callback<List<Equipe>> {
            override fun onFailure(call: Call<List<Equipe>>, t: Throwable) {
                lifeDataList.postValue(null)
            }

            override fun onResponse(call: Call<List<Equipe>>, response: Response<List<Equipe>>) {
                lifeDataList.postValue(response.body())
            }


        })

    }


}