package com.weatherapp.data.repository

import com.weatherapp.data.api.WeatherApi
import com.weatherapp.data.entities.WeatherMainEntity
import io.reactivex.Flowable

class WeatherRemoteImpl(private val weatherApi: WeatherApi) : WeatherInfo {
    override fun getWeatherByCity(city: String): Flowable<WeatherMainEntity> {
        return weatherApi.getWeatherByCity(city)
    }

    override fun getWeatherByCoordinate(lat: Long, lon: Long): Flowable<WeatherMainEntity> {
        return weatherApi.getWeatherByCoordinate(lat, lon)
    }
}