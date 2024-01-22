package com.example.data.model

import com.example.domain.ToDomain
import com.example.domain.model.Meal
import com.google.gson.annotations.SerializedName

data class MealAllDto(
    @SerializedName("meal_roulette_app_meals")
    val meals: List<MealDto>
)

data class MealListDto(
    @SerializedName("meal_roulette_app_meals_aggregate")
    val meals: List<MealDto>
)

data class MealPickDto(
    @SerializedName("meal_roulette_app_meals_by_pk")
    val meal: MealDto
)

data class MealDto(
    val id: Int,
    val picture: String,
    val title: String,
    val description: String? = null,
    val ingredients: String? = null
): ToDomain<Meal> {
    override fun cast(): Meal =
        Meal(
            id, picture, title, description, ingredients
        )

}