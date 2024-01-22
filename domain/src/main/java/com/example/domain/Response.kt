package com.example.domain

sealed class Response<T> {
    data class Loading<T>(val message: String = "loading"): Response<T>()

    data class Data<T>(val data: T): Response<T>()

    data class Error<T>(val throwable: Throwable): Response<T>()
}