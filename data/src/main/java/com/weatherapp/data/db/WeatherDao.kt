package com.weatherapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.weatherapp.data.entities.WeatherDaoEntity

@Dao
interface WeatherDao {
    @Query("SELECT * FROM weather WHERE name=:city")
    fun getWeatherByCity(city: String): WeatherDaoEntity

    @Query("SELECT * FROM weather WHERE lat=:lat AND lon=:lon")
    fun getWeatherByCoordinate(lat: Long, lon: Long): WeatherDaoEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveWeather(weather: WeatherDaoEntity)

    @Query("DELETE FROM weather")
    fun clear()
}
