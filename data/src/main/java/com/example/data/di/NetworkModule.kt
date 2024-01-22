package com.example.data.di

import com.example.data.network.api.FoodApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun retrofit(): Retrofit =
        Retrofit.Builder()
        .baseUrl("https://playground.devskills.co/api/rest/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun foodApiService(retrofit: Retrofit): FoodApiService =
        retrofit.create(FoodApiService::class.java)

}