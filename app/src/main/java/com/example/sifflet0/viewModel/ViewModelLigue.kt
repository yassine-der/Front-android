package com.example.sifflet0.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sifflet0.api.RetrofiteInstance
import com.example.sifflet0.models.Ligue
import com.example.sifflet0.models.Stade
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelLigue : ViewModel() {
    lateinit var lifeDataList: MutableLiveData<List<Ligue>>
    init {
        lifeDataList = MutableLiveData()

    }
    fun getLiveDataObserver(): MutableLiveData<List<Ligue>> {
        return lifeDataList
    }
    fun makeApiCall2(context: Context?){
        val apiInterface = RetrofiteInstance.api(context)
        apiInterface.getLigues().enqueue(object : Callback<List<Ligue>> {
            override fun onFailure(call: Call<List<Ligue>>, t: Throwable) {
                lifeDataList.postValue(null)
            }

            override fun onResponse(call: Call<List<Ligue>>, response: Response<List<Ligue>>) {
                lifeDataList.postValue(response.body())
            }


        })

    }


}