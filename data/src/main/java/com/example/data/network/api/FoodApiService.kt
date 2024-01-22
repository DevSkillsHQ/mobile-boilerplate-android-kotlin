package com.example.data.network.api

import com.example.data.model.MealAllDto
import com.example.data.model.MealListDto
import com.example.data.model.MealPickDto
import retrofit2.http.GET
import retrofit2.http.Path

interface FoodApiService {
    @GET("meal-roulette-app/meals")
    suspend fun getAllMeals(): MealAllDto

    @GET("meal-roulette-app/limit/{limit}/offset/{offset}")
    suspend fun getListMeals(@Path("limit") limit: Int, @Path("offset") offset: Int): MealListDto

    @GET("meal-roulette-app/meals/{id}")
    suspend fun getPickMeal(@Path("id") id: Int): MealPickDto
}