package com.example.domain

sealed class Response<T> {
    class Loading<T>: Response<T>()

    data class Data<T>(val data: T): Response<T>()

    data class Error<T>(val throwable: Throwable): Response<T>()
}