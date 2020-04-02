package com.weatherapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "weather")
data class WeatherDaoEntity(
    @PrimaryKey(autoGenerate = true) val cod: Long,
    @SerializedName("name") val name: String,
    @SerializedName("id")  val id: Float,
    @SerializedName("temp") val temp: Float,
    @SerializedName("feels_like") val feels_like: Float,
    @SerializedName("temp_min") val temp_min: Float,
    @SerializedName("temp_max") val temp_max: Float,
    @SerializedName("pressure") val pressure: Float,
    @SerializedName("humidity") val humidity: Float,
    @SerializedName("lon") val lon: Float,
    @SerializedName("lat") val lat: Float,
    @SerializedName("id") val idSky: Long,
    @SerializedName("main") val main: String,
    @SerializedName("description") val description: String,
    @SerializedName("icon") val icon: String)