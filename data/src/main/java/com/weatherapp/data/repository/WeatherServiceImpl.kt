package com.weatherapp.data.repository

import com.weatherapp.core.common.request
import com.weatherapp.core.exception.Failure
import com.weatherapp.core.fuctional.Either
import com.weatherapp.data.api.WeatherApi
import com.weatherapp.data.entities.CoordinateEntity
import com.weatherapp.data.entities.MeasureEntity
import com.weatherapp.data.entities.WeatherEntity

interface WeatherService {
    suspend fun getWeatherByCity(city: String): Either<Failure, WeatherEntity>

    suspend fun getWeatherByCoordinate(lat: Double, lon: Double): Either<Failure, WeatherEntity>
}

class WeatherServiceImpl(private val weatherApi: WeatherApi) : WeatherService {
    companion object {
        const val APP_KEY = "54964c424bdfec809ab051cf6cc5dcce"
    }

    override suspend fun getWeatherByCity(city: String): Either<Failure, WeatherEntity> {
        val defaultCoordinate = CoordinateEntity(0f, 0f)
        val defaultWeather = MeasureEntity(0f, 0f, 0f,0f,0f,0f)
        val default = WeatherEntity(0, "", 0f, defaultWeather, defaultCoordinate)
        return request(weatherApi.getWeatherByCity(APP_KEY, city), default)
    }

    override suspend fun getWeatherByCoordinate(lat: Double, lon: Double): Either<Failure, WeatherEntity> {
        val defaultCoordinate = CoordinateEntity(0f, 0f)
        val defaultWeather = MeasureEntity(0f, 0f, 0f,0f,0f,0f)
        val default = WeatherEntity(0, "", 0f, defaultWeather, defaultCoordinate)
        return request(weatherApi.getWeatherByCoordinate(APP_KEY, lat, lon), default)
    }
}