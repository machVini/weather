package com.weatherapp.data.entities

import com.google.gson.annotations.SerializedName

data class WeatherMainEntity(
    @SerializedName("cod") val cod: Long,
    @SerializedName("name") val name: String,
    @SerializedName("id")  val id: Long,
    @SerializedName("main")  val main: WeatherEntity,
    @SerializedName("coord")  val coord: CoordinateEntity)