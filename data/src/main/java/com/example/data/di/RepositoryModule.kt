package com.example.data.di

import com.example.data.repository.FoodRepositoryImp
import com.example.domain.repository.FoodRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindFoodRepo(foodRepositoryImp: FoodRepositoryImp): FoodRepository
}