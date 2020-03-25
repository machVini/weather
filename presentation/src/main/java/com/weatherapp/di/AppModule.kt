package com.weatherapp.di

import androidx.room.Room
import com.weatherapp.data.api.WeatherApi
import com.weatherapp.data.db.WeatherDatabase
import com.weatherapp.data.entities.WeatherMapper
import com.weatherapp.data.repository.WeatherRepository
import com.weatherapp.data.repository.WeatherRepositoryImpl
import com.weatherapp.data.repository.WeatherService
import com.weatherapp.data.repository.WeatherServiceImpl
import com.weatherapp.domain.usecases.GetWeatherByCityApiUseCase
import com.weatherapp.domain.usecases.GetWeatherByCityRepoUseCase
import com.weatherapp.viewmodel.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

val appModule = module {
    single {
        Room.databaseBuilder(androidApplication(), WeatherDatabase::class.java, "weather.db")
            .build()
    }

    single {
        get<WeatherDatabase>().getWeatherDao()
    }

    factory { createClient() }

    single {
        createWebService<WeatherApi>(BASE_URL, get())
    }

    single { WeatherMapper() }

    factory<WeatherRepository> { WeatherRepositoryImpl(get(), get()) }

    factory<WeatherService> { WeatherServiceImpl(get()) }

    single { GetWeatherByCityApiUseCase(get()) }

    single { GetWeatherByCityRepoUseCase(get()) }

    viewModel { MainViewModel(get(), get()) }
}