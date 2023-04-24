package com.example.calorieapp.repository

import android.util.Log
import com.example.calorieapp.model.FoodList
import com.example.calorieapp.network.CalorieApi
import javax.inject.Inject

class FoodRepository @Inject constructor(private val api: CalorieApi) {

    suspend fun getAllSearchedFood(searchFood: String): FoodList {
        try {
            return api.getSearchedFood(
                search = searchFood,
                key = "Insert Api Key" //For some reason hiding api key in local properties doesn't work for me
            )

        }catch (e: Exception){
            Log.d("${e.message}", "${e.localizedMessage}")
        }
        return FoodList(items = mutableListOf())
    }

}