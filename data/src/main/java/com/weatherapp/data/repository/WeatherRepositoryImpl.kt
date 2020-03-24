package com.weatherapp.data.repository

import com.weatherapp.data.entities.WeatherMainEntity
import io.reactivex.Flowable

class WeatherRepositoryImpl (private val cacheImpl: WeatherCacheImpl, private val remoteImpl: WeatherRemoteImpl): WeatherRepository {
    override fun getCacheWeatherByCity(city: String): Flowable<WeatherMainEntity> {
        return cacheImpl.getWeatherByCity(city)
    }

    override fun getCacheWeatherByCoordinate(lat: Long, lon: Long): Flowable<WeatherMainEntity> {
        return cacheImpl.getWeatherByCoordinate(lat, lon)
    }

    override fun getRemoteWeatherByCity(city: String): Flowable<WeatherMainEntity> {
        return remoteImpl.getWeatherByCity(city)
    }

    override fun getRemoteWeatherByCoordinate(lat: Long, lon: Long): Flowable<WeatherMainEntity> {
        return remoteImpl.getWeatherByCoordinate(lat, lon)
    }
}