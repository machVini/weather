package com.weatherapp.data.entities

data class WeatherEntity(val cod: Long, val name: String, val id: Float,
                         val main: MeasureEntity, val coord: CoordinateEntity)

//add 'weather entity' and 'sys entity'