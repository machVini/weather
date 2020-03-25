package com.weatherapp.viewmodel

import com.weatherapp.core.platform.BaseViewModel
import com.weatherapp.core.platform.SingleLiveEvent
import com.weatherapp.data.entities.WeatherEntity
import com.weatherapp.domain.usecases.GetWeatherByCityApiUseCase
import com.weatherapp.viewstate.MainViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val useCase: GetWeatherByCityApiUseCase): BaseViewModel() {

    private val viewState = SingleLiveEvent<MainViewState>()
    fun viewState() = viewState

    fun getWeatherByCity(city: String) {
        launch {
            val result = withContext(Dispatchers.IO) {
                useCase.run(GetWeatherByCityApiUseCase.Params(city))
            }
            result.fold(::handleFailure, ::handleSuccess )
        }
    }

    private fun handleSuccess(item: WeatherEntity) {
        viewState.value = MainViewState.Success(item)
    }
}