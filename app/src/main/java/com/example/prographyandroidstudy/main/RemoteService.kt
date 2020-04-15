package com.example.prographyandroidstudy.main

import com.example.prographyandroidstudy.main.trip.TripData
import retrofit2.Call
import retrofit2.http.GET

interface RemoteService {
    @GET("/api/cities")
    fun getTrips(): Call<TripData>
}