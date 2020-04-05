package com.weatherapp.data.entities

data class WeatherEntity(val cod: Long, val name: String, val id: Float,
                         val main: MeasureEntity, val coord: CoordinateEntity,
                         val weather: List<SkyEntity>, val sys: SysEntity)