package com.weatherapp.view

import android.graphics.drawable.Drawable
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.weatherapp.R
import com.weatherapp.common.*
import com.weatherapp.core.platform.BaseActivity
import com.weatherapp.data.entities.WeatherEntity
import com.weatherapp.viewmodel.MainViewModel
import com.weatherapp.viewstate.MainViewState
import org.koin.android.ext.android.inject
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : BaseActivity() {
    private var tvCity: TextView? = null
    private var tvTemp: TextView? = null
    private var tvMax: TextView? = null
    private var tvMin: TextView? = null
    private var tvPressure: TextView? = null
    private var tvHumidity: TextView? = null
    private var tvFeelsLike: TextView? = null
    private var tvDescription: TextView? = null
    private var tvSunrise: TextView? = null
    private var tvSunset: TextView? = null
    private var pbLoading: ProgressBar? = null
    private var tvDateTime: TextView? = null
    private var view: ConstraintLayout? = null

    private val viewModel : MainViewModel by inject()

    override fun layoutId() = R.layout.activity_main

    override fun statusBarColor() = ContextCompat.getColor(this, R.color.colorAccent)

    override fun setupViews() {
        view = findViewById(R.id.cl_main)
        tvCity = findViewById(R.id.tv_address)
        tvDateTime = findViewById(R.id.tv_updated_at)
        tvTemp = findViewById(R.id.tv_temp)
        tvMax = findViewById(R.id.tv_temp_max)
        tvMin = findViewById(R.id.tv_temp_min)
        tvHumidity = findViewById(R.id.tv_humidity)
        tvPressure = findViewById(R.id.tv_pressure)
        tvDescription = findViewById(R.id.tv_status)
        tvSunrise = findViewById(R.id.tv_sunrise)
        tvSunset = findViewById(R.id.tv_sunset)
        tvSunset = findViewById(R.id.tv_sunset)
        tvFeelsLike = findViewById(R.id.tv_feels_like)
        pbLoading = findViewById(R.id.pb_loading)
    }

    override fun findViews() {
        viewModel.getWeatherByCity( "Porto Seguro")
    }

    override fun observeChangesInViewModel() {
        viewModel.viewState().observe(this, Observer {
            when(it) {
                is MainViewState.Success -> receivedWeatherByCity(it.weather)
                is MainViewState.Loading -> updateLoading(it.visibilityId)
            }
        })
    }

    private fun getCurrentDateTime(): String {
        val calendar = Calendar.getInstance()
        val format = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())
        return format.format(calendar.time)
    }

    private fun getCurrencyTime() = Calendar.getInstance().time

    private fun receivedWeatherByCity(item: WeatherEntity) {
        view?.background = getExtensionBg()
        tvCity?.text = String.format(getString(R.string.city), item.name.toUpperCase(Locale.getDefault()), item.sys.country)
        tvDateTime?.text = getCurrentDateTime()
        tvTemp?.text = item.main.temp.convertKelvinToCelsius().celsiusToString()
        tvMin?.text = String.format(getString(R.string.min_temp), item.main.temp_min.convertKelvinToCelsius().celsiusToString())
        tvMax?.text = String.format(getString(R.string.max_temp), item.main.temp_max.convertKelvinToCelsius().celsiusToString())
        tvFeelsLike?.text = item.main.feels_like.convertKelvinToCelsius().celsiusToString()
        tvDescription?.text = item.weather[0].description.capitalizeAll()
        tvSunrise?.text = getTimestampString(item.sys.sunrise)
        tvSunset?.text = getTimestampString(item.sys.sunset)
        tvPressure?.text = item.main.pressure.toString()
        tvHumidity?.text = item.main.humidity.toString()

        viewModel.saveWeather(item)
    }

    private fun updateLoading(status: Int) {
        pbLoading?.visibility = status
    }

    private fun getExtensionBg() : Drawable? {
        return applicationContext.getDrawable(getTimeBackground(getCurrencyTime()))
    }
}