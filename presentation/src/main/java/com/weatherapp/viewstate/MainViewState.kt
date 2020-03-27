package com.weatherapp.viewstate

import com.weatherapp.core.exception.Failure
import com.weatherapp.data.entities.WeatherEntity

sealed class MainViewState {
    class Success(val weather: WeatherEntity) : MainViewState()
    class Loading(val visibilityId: Int) : MainViewState()
    class Error(val error: Failure) : MainViewState()
    class SaveSuccess(val status: Boolean) : MainViewState()
}