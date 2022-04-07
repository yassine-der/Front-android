package com.example.sifflet0.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sifflet0.api.RetrofiteInstance
import com.example.sifflet0.models.Stade
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class mainActitvityViewModel:ViewModel() {
    lateinit var lifeDataList: MutableLiveData<List<Stade>>
    init {
        lifeDataList = MutableLiveData()
    }
    fun getLiveDataObserver():MutableLiveData<List<Stade>>{
        return lifeDataList
    }
    fun makeApiCall(){
        val apiInterface = RetrofiteInstance.api

        apiInterface.getStade().enqueue(object : Callback<List<Stade>> {
            override fun onFailure(call: Call<List<Stade>>, t: Throwable) {
                lifeDataList.postValue(null)
            }

            override fun onResponse(
                call: Call<List<Stade>>,
                response: Response<List<Stade>>
            ) {
                lifeDataList.postValue(response.body())
            }
        })

    }
}