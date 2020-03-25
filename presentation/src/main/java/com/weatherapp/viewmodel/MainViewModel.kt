package com.weatherapp.viewmodel

import android.view.View
import com.weatherapp.core.platform.BaseViewModel
import com.weatherapp.core.platform.SingleLiveEvent
import com.weatherapp.data.entities.WeatherEntity
import com.weatherapp.domain.usecases.GetWeatherByCityApiUseCase
import com.weatherapp.domain.usecases.GetWeatherByCityRepoUseCase
import com.weatherapp.viewstate.MainViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val getWeatherByCityApiUseCase: GetWeatherByCityApiUseCase, private val getWeatherByCityRepoUseCase: GetWeatherByCityRepoUseCase): BaseViewModel() {

    private val viewState = SingleLiveEvent<MainViewState>()
    fun viewState() = viewState

    fun getWeatherByCity(city: String) {
        updateVisibilityId(View.VISIBLE)
        launch {
            val result = withContext(Dispatchers.IO) {
                getWeatherByCityApiUseCase.run(GetWeatherByCityApiUseCase.Params(city))
            }
            result.fold(::handleFailure, ::handleSuccess )
            updateVisibilityId(View.GONE)
        }
    }

    fun getWeatherByCityByRepo(city: String) {
        launch {
            val result = withContext(Dispatchers.IO) {
                getWeatherByCityRepoUseCase.run(GetWeatherByCityRepoUseCase.Params(city))
            }
            result.fold(::handleFailure, ::handleSuccess )
        }
    }

    private fun handleSuccess(item: WeatherEntity) {
        viewState.value = MainViewState.Success(item)
    }

    private fun updateVisibilityId(visibilityId: Int) {
        viewState.value = MainViewState.Loading(visibilityId)
    }
}