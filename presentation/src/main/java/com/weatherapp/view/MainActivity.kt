package com.weatherapp.view

import androidx.core.content.ContextCompat
import com.weatherapp.R
import com.weatherapp.core.platform.BaseActivity
import com.weatherapp.viewmodel.MainViewModel
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity() {

    private val viewModel : MainViewModel by inject()

    override fun layoutId() = R.layout.activity_main

    override fun statusBarColor() = ContextCompat.getColor(this, R.color.colorAccent)

    override fun setupViews() {

    }

    override fun findViews() {

    }
}