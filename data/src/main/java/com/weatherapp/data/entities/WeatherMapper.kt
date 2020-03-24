package com.weatherapp.data.entities

class WeatherMapper constructor() {
    fun mapRemoteToDao(remote: WeatherMainEntity) : WeatherDaoEntity =
        WeatherDaoEntity(
            remote.cod,
            remote.name,
            remote.id,
            remote.main.temp,
            remote.main.feels_like,
            remote.main.temp_min,
            remote.main.temp_max,
            remote.main.pressure,
            remote.main.humidity,
            remote.coord.lon,
            remote.coord.lat)


    fun mapDaoToRemote(dao: WeatherDaoEntity) : WeatherMainEntity {
        val weatherEntity = WeatherEntity(dao.temp, dao.feels_like, dao.temp_min, dao.temp_max, dao.pressure, dao.humidity)
        val coordinateEntity = CoordinateEntity(dao.lon, dao.lat)
        return WeatherMainEntity(dao.cod, dao.name, dao.id, weatherEntity, coordinateEntity)
    }
}