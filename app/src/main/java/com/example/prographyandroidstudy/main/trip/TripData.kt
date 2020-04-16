package com.example.prographyandroidstudy.main.trip


import com.example.prographyandroidstudy.main.trip.City
import com.google.gson.annotations.SerializedName

data class TripData(
    @SerializedName("cities")
    var cities: List<City>
)