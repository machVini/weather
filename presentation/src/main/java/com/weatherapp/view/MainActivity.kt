package com.weatherapp.view

import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.weatherapp.R
import com.weatherapp.common.capitalizeAll
import com.weatherapp.common.celsiusToString
import com.weatherapp.common.convertKelvinToCelsius
import com.weatherapp.core.platform.BaseActivity
import com.weatherapp.data.entities.WeatherEntity
import com.weatherapp.viewmodel.MainViewModel
import com.weatherapp.viewstate.MainViewState
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity() {
    private var tvCity: TextView? = null
    private var tvTemp: TextView? = null
    private var tvMax: TextView? = null
    private var tvMin: TextView? = null
    private var tvPressure: TextView? = null
    private var tvHumidity: TextView? = null
    private var tvDescription: TextView? = null
    private var pbLoading: ProgressBar? = null

    private val viewModel : MainViewModel by inject()

    override fun layoutId() = R.layout.activity_main

    override fun statusBarColor() = ContextCompat.getColor(this, R.color.colorAccent)

    override fun setupViews() {
        tvCity = findViewById(R.id.tv_address)
        tvTemp = findViewById(R.id.tv_temp)
        tvMax = findViewById(R.id.tv_temp_max)
        tvMin = findViewById(R.id.tv_temp_min)
        tvHumidity = findViewById(R.id.tv_humidity)
        tvPressure = findViewById(R.id.tv_pressure)
        tvDescription = findViewById(R.id.tv_status)
        pbLoading = findViewById(R.id.pb_loading)
    }

    override fun findViews() {
        viewModel.getWeatherByLocation( 42.332, -71.0202)
    }

    override fun observeChangesInViewModel() {
        viewModel.viewState().observe(this, Observer {
            when(it) {
                is MainViewState.Success -> receivedWeatherByCity(it.weather)
                is MainViewState.Loading -> updateLoading(it.visibilityId)
            }
        })
    }

    private fun receivedWeatherByCity(item: WeatherEntity) {
        tvCity?.text = item.name
        tvCity?.text = item.name.toUpperCase()
        tvTemp?.text = item.main.temp.convertKelvinToCelsius().celsiusToString()
        tvMin?.text = String.format(getString(R.string.min_temp), item.main.temp_min.convertKelvinToCelsius().celsiusToString())
        tvMax?.text = String.format(getString(R.string.max_temp), item.main.temp_max.convertKelvinToCelsius().celsiusToString())
        tvDescription?.text = item.main.feels_like.toString().capitalizeAll()
        tvPressure?.text = item.main.pressure.toString()
        tvHumidity?.text = item.main.humidity.toString()

        viewModel.saveWeather(item)
    }

    private fun updateLoading(status: Int) {
        pbLoading?.visibility = status
    }
}