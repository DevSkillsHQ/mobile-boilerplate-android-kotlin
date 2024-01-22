package com.example.data.repository

import com.example.data.network.api.FoodApiService
import com.example.data.network.apiCall
import com.example.domain.Response
import com.example.domain.model.Meal
import com.example.domain.repository.FoodRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FoodRepositoryImp @Inject constructor(
    private val apiService: FoodApiService
): FoodRepository {
    override suspend fun getAllMeals(): Flow<Response<List<Meal>>> = apiCall {
        apiService.getAllMeals().meals.map {
            it.cast()
        }
    }

    override suspend fun getMeals(limit: Int, offset: Int): Flow<Response<List<Meal>>> = apiCall{
        apiService.getListMeals(limit, offset).meals.map {
            it.cast()
        }
    }

    override suspend fun getMealWithId(id: Int): Flow<Response<Meal>> = apiCall {
        apiService.getPickMeal(id).meal.cast()
    }

}