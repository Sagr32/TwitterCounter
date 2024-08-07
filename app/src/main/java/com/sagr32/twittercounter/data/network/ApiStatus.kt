package com.sagr32.twittercounter.data.network

sealed class ApiStatus<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : ApiStatus<T>(data = data)
    class Error<T>(errorMessage: String) : ApiStatus<T>(message = errorMessage)
    class Loading<T> : ApiStatus<T>()
}
