package com.weatherapp.core.exception

sealed class Failure {
    object NetworkConnection : Failure()
    class ServerError(val errorCode: Int) : Failure()

    /** * Extend this class for feature specific failures.*/
    abstract class FeatureFailure: Failure()
}