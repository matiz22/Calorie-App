package com.example.calorieapp.network

import com.example.calorieapp.model.FoodList
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface CalorieApi {
    @GET("v1/nutrition")
    suspend fun getSearchedFood(
        @Header("X-Api-Key") key: String,
        @Query("query") search: String
    ): FoodList

}