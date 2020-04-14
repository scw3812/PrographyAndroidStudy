package com.example.prographyandroidstudy

import retrofit2.Call
import retrofit2.http.GET
import java.util.*

interface RemoteService {
    @GET("/api/cities")
    fun getTrips(): Call<TripData>
}