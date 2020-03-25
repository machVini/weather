package com.weatherapp.core.common

import com.weatherapp.core.exception.Failure
import com.weatherapp.core.fuctional.Either
import retrofit2.Call

fun <T> request(call: Call<T>, default: T): Either<Failure, T> {
    return try {
        val response = call.execute()
        when(response.isSuccessful) {
            true -> Either.Right(response.body() ?: default)
            false -> Either.Left(Failure.ServerError(response.code()))
        }
    } catch (exception: Throwable) {
        Either.Left(Failure.ServerError(-1))
    }
}