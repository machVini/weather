package com.weatherapp.data.api

import com.weatherapp.data.entities.WeatherEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather/")
    fun getWeatherByCity(@Query("appid") key: String, @Query("q") city: String): Call<WeatherEntity>

    @GET("weather/")
    fun getWeatherByCoordinate(@Query("appid") key: String, @Query("lat") lat: Double,
                               @Query("lon") lon: Double
    ): Call<WeatherEntity>
}