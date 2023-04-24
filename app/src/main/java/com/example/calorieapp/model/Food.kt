package com.example.calorieapp.model

data class Food(
    var calories: Double,
    var carbohydrates_total_g: Double,
    var fat_total_g: Double,
    val name: String,
    var protein_g: Double,
    var serving_size_g: Double,
)