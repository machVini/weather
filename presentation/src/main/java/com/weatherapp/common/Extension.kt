package com.weatherapp.common

import com.weatherapp.R
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

private const val KC_DIFF = 273
private const val CELSIUS_UNIT = "°C"
private const val NIGHTLY_BACKGROUND = R.drawable.bg_night
private const val DAILY_BACKGROUND = R.drawable.bg_day

fun Float.convertKelvinToCelsius() : Int = (this - KC_DIFF).toInt()

fun Number.celsiusToString() : String = this.toString() + CELSIUS_UNIT

fun String.capitalizeAll() : String {
    val words = this.split(" ").toMutableList()

    var output = ""

    for (word in words) {
        output += word.capitalize() + " "
    }

    return output.trim()
}

fun Long.toTimestamp(): Timestamp = Timestamp(this)

fun getTimestampString(l: Long): String? {
    val timestamp = l.toTimestamp()
    val format = SimpleDateFormat("HH:mm a", Locale.getDefault())
    return format.format(Date(timestamp.time))
}

fun getTimeBackground(d: Date) : Int{
    val hour = d.hours
    if (hour < 6 || hour >= 18) return NIGHTLY_BACKGROUND
    return DAILY_BACKGROUND
}

