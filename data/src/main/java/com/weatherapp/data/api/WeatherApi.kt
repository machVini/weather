package com.weatherapp.data.api

import com.weatherapp.data.entities.WeatherMainEntity
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("http://api.openweathermap.org/data/2.5/weather")
    fun getWeatherByCity(@Query("q") city: String): Flowable<WeatherMainEntity>

    @GET("http://api.openweathermap.org/data/2.5/weather")
    fun getWeatherByCoordinate(@Query("lat") lat: Long,
                               @Query("lon") lon: Long): Flowable<WeatherMainEntity>
}