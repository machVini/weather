package com.weatherapp.data.repository

import com.weatherapp.data.entities.WeatherMainEntity
import io.reactivex.Flowable

interface WeatherInfo {
    fun getWeatherByCity(city: String): Flowable<WeatherMainEntity>

    fun getWeatherByCoordinate(lat: Long, lon: Long): Flowable<WeatherMainEntity>
}