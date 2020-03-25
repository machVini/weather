package com.weatherapp.view

import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.weatherapp.R
import com.weatherapp.core.platform.BaseActivity
import com.weatherapp.data.entities.WeatherEntity
import com.weatherapp.viewmodel.MainViewModel
import com.weatherapp.viewstate.MainViewState
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity() {

    private var tvCity: TextView? = null
    private var pbLoading: ProgressBar? = null

    private val viewModel : MainViewModel by inject()

    override fun layoutId() = R.layout.activity_main

    override fun statusBarColor() = ContextCompat.getColor(this, R.color.colorAccent)

    override fun setupViews() {
        tvCity = findViewById(R.id.tv_address)
        pbLoading = findViewById(R.id.pb_loading)
    }

    override fun findViews() {
        viewModel.getWeatherByCity("Mococa")
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
    }

    private fun updateLoading(status: Int) {
        pbLoading?.visibility = status
    }
}