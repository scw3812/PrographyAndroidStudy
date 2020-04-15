package com.example.prographyandroidstudy.main.trip


import com.google.gson.annotations.SerializedName

data class City(
    @SerializedName("city")
    val city: String,
    @SerializedName("no")
    val no: Int,
    @SerializedName("url")
    val url: String,
    val isMark: Boolean = false
)