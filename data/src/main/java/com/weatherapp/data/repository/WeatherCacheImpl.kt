package com.weatherapp.data.repository

import com.weatherapp.data.db.WeatherDao
import com.weatherapp.data.entities.WeatherMainEntity
import com.weatherapp.data.entities.WeatherMapper
import io.reactivex.Flowable

class WeatherCacheImpl(private val weatherDao: WeatherDao, private val mapper: WeatherMapper) : WeatherInfo {
    override fun getWeatherByCity(city: String): Flowable<WeatherMainEntity> {
        return weatherDao.getWeatherByCity(city).map {
            mapper.mapDaoToRemote(it)
        }
    }

    override fun getWeatherByCoordinate(lat: Long, lon: Long): Flowable<WeatherMainEntity> {
        return weatherDao.getWeatherByCoordinate(lat, lon).map {
            mapper.mapDaoToRemote(it)
        }
    }
}