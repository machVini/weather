package com.weatherapp.data.entities

import com.google.gson.annotations.SerializedName

data class CoordinateEntity(
    @SerializedName("lon") val lon: Long,
    @SerializedName("lat") val lat: Long)