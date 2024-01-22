package com.example.data.di

import com.example.data.network.api.FoodApiService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl("https://playground.devskills.co/api/rest/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<FoodApiService> {
        get<Retrofit>().create(FoodApiService::class.java)
    }
}