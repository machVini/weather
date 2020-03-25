package com.weatherapp.domain.usecases

import com.weatherapp.core.exception.Failure
import com.weatherapp.core.fuctional.Either
import com.weatherapp.core.iteractor.UseCase
import com.weatherapp.data.entities.WeatherEntity
import com.weatherapp.data.repository.WeatherRepository

class GetWeatherByCityRepoUseCase(private val repository: WeatherRepository): UseCase<WeatherEntity, GetWeatherByCityRepoUseCase.Params>() {

    data class Params(val city: String)

    override suspend fun run(params: Params): Either<Failure, WeatherEntity> {
        return try {
            val response = repository.getWeatherByCity(params.city)
            Either.Right(response)
        } catch (exception: Throwable) {
            Either.Left(Failure.ServerError(-1))
        }
    }
}