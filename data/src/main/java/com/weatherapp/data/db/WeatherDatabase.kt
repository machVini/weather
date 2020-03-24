package com.weatherapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.weatherapp.data.entities.WeatherDaoEntity

@Database(entities = [WeatherDaoEntity::class], version = 1, exportSchema = false)
abstract class WeatherDatabase: RoomDatabase() {
    abstract fun getWeatherDao(): WeatherDao
}