package com.weatherapp.di

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun createClient() : OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
    val clientBuilder = OkHttpClient.Builder()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    clientBuilder.addInterceptor(httpLoggingInterceptor)
    return clientBuilder.build()
}

inline fun <reified T> createWebService(baseUrl: String, client: OkHttpClient): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    return retrofit.create(T::class.java)
}