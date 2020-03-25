package com.weatherapp.data.repository

import com.weatherapp.data.db.WeatherDao
import com.weatherapp.data.entities.WeatherEntity
import com.weatherapp.data.entities.WeatherMapper
import java.lang.Exception

interface WeatherRepository {
    suspend fun getWeatherByCity(city: String): WeatherEntity

    suspend fun getWeatherByCoordinate(lat: Long, lon: Long): WeatherEntity

    suspend fun saveWeather(item: WeatherEntity): Boolean
}

class WeatherRepositoryImpl (private val dao: WeatherDao, private val mapper: WeatherMapper): WeatherRepository {
    override suspend fun getWeatherByCity(city: String): WeatherEntity {
        return mapper.mapDaoToRemote(dao.getWeatherByCity(city))
    }

    override suspend fun getWeatherByCoordinate(lat: Long, lon: Long): WeatherEntity {
        return mapper.mapDaoToRemote(dao.getWeatherByCoordinate(lat, lon))
    }

    override suspend fun saveWeather(item: WeatherEntity): Boolean {
        try {
            dao.clear()
            dao.saveWeather(mapper.mapRemoteToDao(item))
        } catch (e: Exception) {
            return false
        }
        return true
    }
}