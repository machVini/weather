package com.weatherapp.data.repository

import com.weatherapp.data.entities.WeatherMainEntity
import io.reactivex.Flowable

interface WeatherRepository {
    fun getCacheWeatherByCity(city: String): Flowable<WeatherMainEntity>

    fun getCacheWeatherByCoordinate(lat: Long, lon: Long): Flowable<WeatherMainEntity>

    fun getRemoteWeatherByCity(city: String): Flowable<WeatherMainEntity>

    fun getRemoteWeatherByCoordinate(lat: Long, lon: Long): Flowable<WeatherMainEntity>
}