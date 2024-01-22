package com.example.data.network

import com.example.domain.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

suspend fun <T>apiCall(request: suspend () -> T): Flow<Response<T>> = flow {
    emit(Response.Loading())
    try {
        emit(Response.Data(request.invoke()))
    }
    catch(throwable: Throwable){
        // this can be expanded for different type of errors
        emit(Response.Error(throwable))
    }
}