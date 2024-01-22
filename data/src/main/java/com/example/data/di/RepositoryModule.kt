package com.example.data.di

import com.example.data.network.api.FoodApiService
import com.example.data.repository.FoodRepositoryImp
import com.example.domain.repository.FoodRepository
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    single { FoodRepositoryImp(get<FoodApiService>()) } bind FoodRepository::class
}