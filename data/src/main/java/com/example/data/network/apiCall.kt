package com.example.data.network

import android.util.Log
import com.example.domain.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

suspend fun <T>apiCall(request: suspend () -> T): Flow<Response<T>> = flow {
    emit(Response.Loading())
    try {
        val response = request.invoke()
        emit(Response.Data(response))
    }
    catch(throwable: Throwable){
        // this can be expanded for different type of errors
        emit(Response.Error(throwable))
    }
}