package com.weatherapp.data.repository

import com.weatherapp.data.api.WeatherApi
import com.weatherapp.data.entities.WeatherMainEntity
import io.reactivex.Flowable

class WeatherRemoteImpl(private val weatherApi: WeatherApi) : WeatherInfo {
    companion object {
        const val API_KEY = "54964c424bdfec809ab051cf6cc5dcce"
    }

    override fun getWeatherByCity(city: String): Flowable<WeatherMainEntity> {
        return weatherApi.getWeatherByCity(city, API_KEY)
    }

    override fun getWeatherByCoordinate(lat: Long, lon: Long): Flowable<WeatherMainEntity> {
        return weatherApi.getWeatherByCoordinate(lat, lon, API_KEY)
    }
}