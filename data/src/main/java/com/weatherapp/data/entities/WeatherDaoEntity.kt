package com.weatherapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "weather")
data class WeatherDaoEntity(
    @PrimaryKey(autoGenerate = true) val cod: Long,
    @SerializedName("name") val name: String,
    @SerializedName("lon") val lon: Float,
    @SerializedName("lat") val lat: Float,
    @SerializedName("weather") val weather: String)
