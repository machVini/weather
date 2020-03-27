package com.weatherapp.viewmodel

import android.view.View
import com.weatherapp.core.exception.Failure
import com.weatherapp.core.platform.BaseViewModel
import com.weatherapp.core.platform.SingleLiveEvent
import com.weatherapp.data.entities.WeatherEntity
import com.weatherapp.domain.usecases.GetWeatherByCityApiUseCase
import com.weatherapp.domain.usecases.GetWeatherByCityRepoUseCase
import com.weatherapp.domain.usecases.SaveWeatherUserCase
import com.weatherapp.domain.usecases.GetWeatherByLocationApiUseCase
import com.weatherapp.viewstate.MainViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val getWeatherByCityApiUseCase: GetWeatherByCityApiUseCase,
                    private val getWeatherByCityRepoUseCase: GetWeatherByCityRepoUseCase,
                    private val saveWeatherUserCase: SaveWeatherUserCase,
                    private val getWeatherByLocationApiUseCase: GetWeatherByLocationApiUseCase): BaseViewModel() {

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

    fun getWeatherByLocation(lat: Double, lon: Double){
        launch {
            val result = withContext(Dispatchers.IO){
                getWeatherByLocationApiUseCase.run(GetWeatherByLocationApiUseCase.Params(lat,lon))
            }
            result.fold(::handleFailure, ::handleSuccess )
        }
    }

    fun saveWeather(weatherEntity: WeatherEntity) {
        launch {
            val result = withContext(Dispatchers.IO) {
                saveWeatherUserCase.run(SaveWeatherUserCase.Params(weatherEntity))
            }
            result.fold(::handleFailure, ::handleSaveWeatherSuccess )
        }
    }

    private fun handleSuccess(item: WeatherEntity) {
        viewState.value = MainViewState.Success(item)
    }

    private fun updateVisibilityId(visibilityId: Int) {
        viewState.value = MainViewState.Loading(visibilityId)
    }

    override fun handleFailure(failure: Failure) {
        viewState.value = MainViewState.Error(failure)
    }

    private fun handleSaveWeatherSuccess(status: Boolean) {
        viewState.value = MainViewState.SaveSuccess(status)
    }
}