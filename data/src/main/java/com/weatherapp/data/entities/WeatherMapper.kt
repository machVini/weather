package com.weatherapp.data.entities

import com.google.gson.Gson

class WeatherMapper constructor() {
    fun mapRemoteToDao(remote: WeatherEntity) : WeatherDaoEntity =

        WeatherDaoEntity(
            remote.cod,
            remote.name,
            remote.coord.lon,
            remote.coord.lat,
            Gson().toJson(remote))


    fun mapDaoToRemote(dao: WeatherDaoEntity) : WeatherEntity {
        val weatherEntity = Gson().fromJson(dao.weather, WeatherEntity::class.java)
        return WeatherEntity(
            weatherEntity.cod,
            weatherEntity.name,
            weatherEntity.id,
            weatherEntity.main,
            weatherEntity.coord,
            weatherEntity.weather,
            weatherEntity.sys)
    }
}