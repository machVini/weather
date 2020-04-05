package com.weatherapp.common

import java.sql.Timestamp

private const val KC_DIFF = 273
private const val CELSIUS_UNIT = "°C"

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

fun longToTimestamp(l: Long): Timestamp? = Timestamp(l)

fun getTimestampString(l: Long): String? {
    val timestamp = longToTimestamp(l)
    return timestamp?.hours.toString() + ":" + timestamp?.minutes.toString()
}