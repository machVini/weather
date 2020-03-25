package com.weatherapp.domain.usecases

import com.weatherapp.core.exception.Failure
import com.weatherapp.core.fuctional.Either
import com.weatherapp.core.iteractor.UseCase
import com.weatherapp.data.entities.WeatherEntity
import com.weatherapp.data.repository.WeatherService

class GetWeatherByCityApiUseCase(private val service: WeatherService): UseCase<WeatherEntity, GetWeatherByCityApiUseCase.Params>() {

    data class Params(val city: String)

    override suspend fun run(params: Params): Either<Failure, WeatherEntity> = service.getWeatherByCity(params.city)
}