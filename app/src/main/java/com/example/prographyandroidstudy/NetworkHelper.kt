package com.example.prographyandroidstudy

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkHelper {
    companion object{
        private val networkHelper = NetworkHelper()
        fun getInstance() : NetworkHelper{
            return networkHelper
        }
    }

    private val retrofit = Retrofit.Builder().baseUrl("https://progserver.herokuapp.com")
        .addConverterFactory(GsonConverterFactory.create()).build()
    private val remoteService : RemoteService= retrofit.create(RemoteService::class.java)

    fun getService() : RemoteService{
        return remoteService
    }
}