package com.weatherapp.data.repository

import com.weatherapp.core.common.request
import com.weatherapp.core.exception.Failure
import com.weatherapp.core.fuctional.Either
import com.weatherapp.data.api.WeatherApi
import com.weatherapp.data.entities.*

interface WeatherService {
    suspend fun getWeatherByCity(city: String): Either<Failure, WeatherEntity>

    suspend fun getWeatherByCoordinate(lat: Double, lon: Double): Either<Failure, WeatherEntity>
}

class WeatherServiceImpl(private val weatherApi: WeatherApi) : WeatherService {
    companion object {
        const val APP_KEY = "54964c424bdfec809ab051cf6cc5dcce"
    }

    override suspend fun getWeatherByCity(city: String): Either<Failure, WeatherEntity> {
        val coordinateEntity = CoordinateEntity(0f, 0f)
        val measureEntity = MeasureEntity(0f, 0f, 0f,0f,0f,0f)
        val listWeather = listOf<SkyEntity>()
        val sysEntity = SysEntity(0, 0, "", 0, 0)
        val default = WeatherEntity(0, "", 0f, measureEntity, coordinateEntity, listWeather, sysEntity)
        return request(weatherApi.getWeatherByCity(APP_KEY, city), default)
    }

    override suspend fun getWeatherByCoordinate(lat: Double, lon: Double): Either<Failure, WeatherEntity> {
        val coordinateEntity = CoordinateEntity(0f, 0f)
        val measureEntity = MeasureEntity(0f, 0f, 0f,0f,0f,0f)
        val listWeather = listOf<SkyEntity>()
        val sysEntity = SysEntity(0, 0, "", 0, 0)
        val default = WeatherEntity(0, "", 0f, measureEntity, coordinateEntity, listWeather, sysEntity)
        return request(weatherApi.getWeatherByCoordinate(APP_KEY, lat, lon), default)
    }
}