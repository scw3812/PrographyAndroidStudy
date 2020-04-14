package com.example.prographyandroidstudy


import com.google.gson.annotations.SerializedName

data class City(
    @SerializedName("city")
    val city: String,
    @SerializedName("no")
    val no: Int,
    @SerializedName("url")
    val url: String
)