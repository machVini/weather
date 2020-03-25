package com.weatherapp.viewstate

import com.weatherapp.core.exception.Failure
import com.weatherapp.data.entities.WeatherEntity

sealed class MainViewState {
    class Success(val weather: WeatherEntity) : MainViewState()
    object Loading : MainViewState()
    class Error(val error: Failure) : MainViewState()
}