package com.example.domain.model


data class Meal(
    val id: Int,
    val picture: String,
    val title: String,
    val description: String? = null,
    val ingredients: String? = null
)