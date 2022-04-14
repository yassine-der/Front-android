package com.example.sifflet0.viewModel

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sifflet0.PREF_NAME
import com.example.sifflet0.api.RetrofiteInstance
import com.example.sifflet0.models.Stade
import dagger.hilt.android.internal.Contexts.getApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class mainActitvityViewModel:ViewModel() {
    lateinit var  sharedPreferences: SharedPreferences

    lateinit var lifeDataList: MutableLiveData<List<Stade>>
    init {
        lifeDataList = MutableLiveData()

    }
    fun getLiveDataObserver():MutableLiveData<List<Stade>>{
        return lifeDataList
    }

    fun makeApiCall(context: Context?){
        val apiInterface = RetrofiteInstance.api(context)
        //sharedPreferences = getApplication(this).getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
       // apiInterface.getStade(sharedPreferences.getString("token",null)!!).enqueue(object : Callback<List<Stade>> {
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