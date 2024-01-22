package com.example.domain.repository

import com.example.domain.Response
import com.example.domain.model.Meal
import kotlinx.coroutines.flow.Flow

interface FoodRepository {
    suspend fun getAllMeals(): Flow<Response<List<Meal>>>
    suspend fun getMeals(limit: Int, offset: Int): Flow<Response<List<Meal>>>
    suspend fun getMealWithId(id: Int): Flow<Response<Meal>>
}