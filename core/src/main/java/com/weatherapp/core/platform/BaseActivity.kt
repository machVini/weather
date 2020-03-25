package com.weatherapp.core.platform

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {
    abstract fun layoutId(): Int

    abstract fun statusBarColor(): Int

    abstract fun setupViews()

    abstract fun findViews()

    open fun observeChangesInViewModel() { }

    override fun setContentView(layoutResId: Int) {
        super.setContentView(layoutResId)
    }

    fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    fun setStatusBarColor(@ColorInt colorId: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.apply {
                addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                statusBarColor = colorId
            }
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        setStatusBarColor(statusBarColor())
        findViews()
        setupViews()
        observeChangesInViewModel()
    }

    fun requestPermission(permissions: Array<String>) {
        ActivityCompat.requestPermissions(this, permissions, 0)
    }

    fun checkPermissions(permission: String): Boolean {
        return (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED)
    }
}