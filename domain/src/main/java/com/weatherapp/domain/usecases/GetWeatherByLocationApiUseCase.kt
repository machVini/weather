package com.weatherapp.domain.usecases

import com.weatherapp.core.exception.Failure
import com.weatherapp.core.fuctional.Either
import com.weatherapp.core.iteractor.UseCase
import com.weatherapp.data.entities.WeatherEntity
import com.weatherapp.data.repository.WeatherService

class GetWeatherByLocationApiUseCase(private val service: WeatherService): UseCase<WeatherEntity, GetWeatherByLocationApiUseCase.Params>() {

    data class Params(val lat: Double, val lon: Double)

    override suspend fun run(params: Params): Either<Failure, WeatherEntity> {
        return service.getWeatherByCoordinate(params.lat, params.lon)
    }
}