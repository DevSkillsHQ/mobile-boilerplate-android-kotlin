package com.example.data.repository

import app.cash.turbine.test
import com.example.data.model.MealAllDto
import com.example.data.model.MealDto
import com.example.data.model.MealListDto
import com.example.data.model.MealPickDto
import com.example.data.model.MealRouletteAppMealsAggregate
import com.example.data.network.api.FoodApiService
import com.example.domain.Response
import com.example.domain.model.Meal
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.internal.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.kotlin.whenever

class FoodRepositoryImpTest {

    private val apiService: FoodApiService = mock(FoodApiService::class.java)

    private fun createRepo() = FoodRepositoryImp(apiService)

    private val mealList = listOf(MealDto(1,"picture","title","des","ingredient"))

    @Test
    fun `test if we can get all of the meals`() = runBlocking {
        val fakeData = MealAllDto(mealList)
        whenever(apiService.getAllMeals()).thenReturn(fakeData)
        createRepo().getAllMeals().test {
            assertEquals(this.awaitItem(), Response.Loading<List<Meal>>())
            assertEquals(this.awaitItem(), Response.Data<List<Meal>>(mealList.map { it.cast() }))
            awaitComplete()
        }
    }

    @Test
    fun `test if we can get a list of the meals`() = runBlocking{
        val fakeData = MealListDto(MealRouletteAppMealsAggregate(mealList))
        whenever(apiService.getListMeals(1,1)).thenReturn(fakeData)
        createRepo().getMeals(1,1).test {
            assertEquals(this.awaitItem(), Response.Loading<List<Meal>>())
            assertEquals(this.awaitItem(), Response.Data<List<Meal>>(mealList.map { it.cast() }))
            awaitComplete()
        }
    }

    @Test
    fun `test if we can get one meal by its id`() = runBlocking {
        whenever(apiService.getPickMeal(1)).thenReturn(MealPickDto(mealList.first()))
        createRepo().getMealWithId(1).test {
            assertEquals(this.awaitItem(), Response.Loading<Meal>())
            assertEquals(this.awaitItem(), Response.Data<Meal>(mealList.first().cast()))
            awaitComplete()
        }
    }
}