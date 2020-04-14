package com.example.prographyandroidstudy


import com.google.gson.annotations.SerializedName

data class TripData(
    @SerializedName("cities")
    val cities: List<City>
)