package com.weatherapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "weather")
data class WeatherDaoEntity(
    @PrimaryKey(autoGenerate = true) val cod: Long,
    @SerializedName("name") val name: String,
    @SerializedName("id")  val id: Long,
    @SerializedName("temp") val temp: Long,
    @SerializedName("feels_like") val feels_like: Long,
    @SerializedName("temp_min") val temp_min: Long,
    @SerializedName("temp_max") val temp_max: Long,
    @SerializedName("pressure") val pressure: Long,
    @SerializedName("humidity") val humidity: Long,
    @SerializedName("lon") val lon: Long,
    @SerializedName("lat") val lat: Long)