package com.example.sifflet0.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sifflet0.api.RetrofiteInstance
import com.example.sifflet0.models.Stade
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class viewModelUser : ViewModel(){
    lateinit var lifeDataList: MutableLiveData<List<Stade>>
    init {
        lifeDataList = MutableLiveData()

    }
    fun getLiveDataObserver(): MutableLiveData<List<Stade>> {
        return lifeDataList
    }

    fun makeApiCall99(context: Context?){
        val apiInterface = RetrofiteInstance.api(context)
        //sharedPreferences = getApplication(this).getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        // apiInterface.getStade(sharedPreferences.getString("token",null)!!).enqueue(object : Callback<List<Stade>> {
        apiInterface.getStadeUser().enqueue(object : Callback<List<Stade>> {
            override fun onFailure(call: Call<List<Stade>>, t: Throwable) {
                lifeDataList.postValue(null)
                println("(((((((((((((((((((((((((((((((")
                println(t)
                println("(((((((((((((((((((((((((((((((")
            }

            override fun onResponse(
                call: Call<List<Stade>>,
                response: Response<List<Stade>>
            ) {
                println(")))))))))))))))))))))))))))")
                println(response.body())
                println("(((((((((((((((((((((((((((((((")

                lifeDataList.postValue(response.body())
            }
        })

    }
}