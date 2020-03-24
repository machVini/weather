package com.weatherapp.data.entities

import com.google.gson.annotations.SerializedName

data class WeatherEntity(
    @SerializedName("temp") val temp: Long,
    @SerializedName("feels_like") val feels_like: Long,
    @SerializedName("temp_min") val temp_min: Long,
    @SerializedName("temp_max") val temp_max: Long,
    @SerializedName("pressure") val pressure: Long,
    @SerializedName("humidity") val humidity: Long)