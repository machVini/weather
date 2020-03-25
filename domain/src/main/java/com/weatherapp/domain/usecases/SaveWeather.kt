package com.weatherapp.domain.usecases

import com.weatherapp.core.exception.Failure
import com.weatherapp.core.fuctional.Either
import com.weatherapp.core.iteractor.UseCase
import com.weatherapp.data.entities.WeatherEntity
import com.weatherapp.data.repository.WeatherRepository

class SaveWeather(private val repository: WeatherRepository): UseCase<Boolean, SaveWeather.Params>() {

    data class Params(val weather: WeatherEntity)

    override suspend fun run(params: Params): Either<Failure, Boolean> {
        return try {
            repository.saveWeather(params.weather)
            Either.Right(true)
        } catch (exception: Throwable) {
            Either.Left(Failure.ServerError(-1))
        }
    }
}