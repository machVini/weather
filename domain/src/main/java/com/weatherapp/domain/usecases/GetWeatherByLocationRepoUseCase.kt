package com.weatherapp.domain.usecases

import com.weatherapp.core.exception.Failure
import com.weatherapp.core.fuctional.Either
import com.weatherapp.core.iteractor.UseCase
import com.weatherapp.data.entities.WeatherEntity
import com.weatherapp.data.repository.WeatherRepository

class GetWeatherByLocationRepoUseCase(private val repository: WeatherRepository): UseCase<WeatherEntity, GetWeatherByLocationRepoUseCase.Params>() {

    data class Params(val lat: Long, val lon: Long)

    override suspend fun run(params: Params): Either<Failure, WeatherEntity> {
        return try {
            val response = repository.getWeatherByCoordinate(params.lat, params.lon)
            Either.Right(response)
        } catch (exception: Throwable) {
            Either.Left(Failure.ServerError(-1))
        }
    }
}