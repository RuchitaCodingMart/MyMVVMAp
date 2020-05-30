package com.example.mymvvmap.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mymvvmap.data.network.MyApi
import retrofit2.Call
import retrofit2.Response


class UserRepository {
    fun userLogin(email : String , password : String ): LiveData<String>{
        val loginResponse = MutableLiveData<String>()

        MyApi().userLogin(email, password)
            .enqueue(object : retrofit2.Callback<okhttp3.ResponseBody>{
                override fun onFailure(call: Call<okhttp3.ResponseBody>, t: Throwable) {
                    loginResponse.value = t.message
                }

                override fun onResponse(
                    call: Call<okhttp3.ResponseBody>,
                    response: Response<okhttp3.ResponseBody>){
                    if(response.isSuccessful){
                        loginResponse.value = response.body()?.string()
                    }
                }

            })
        return loginResponse
    }
}