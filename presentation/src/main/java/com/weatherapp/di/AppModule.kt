package com.weatherapp.di

import androidx.room.Room
import com.weatherapp.data.api.WeatherApi
import com.weatherapp.data.db.WeatherDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

private const val BASE_URL = "http://api.openweathermap.org/data/2.5/"

val appModule = module {

    single {
        Room.databaseBuilder(androidApplication(), WeatherDatabase::class.java, "weather.db")
            .build()
    }

    single {
        get<WeatherDatabase>().getWeatherDao()
    }

    single {
        createWebService<WeatherApi>(
            get(), BASE_URL
        )
    }
}