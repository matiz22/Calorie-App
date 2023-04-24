package com.example.calorieapp.util

import androidx.room.TypeConverter
import com.example.calorieapp.model.Food
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FoodListConverter {
    @TypeConverter
    fun fromItemList(foodList: List<Food>): String {
        val gson = Gson()
        return gson.toJson(foodList)
    }

    @TypeConverter
    fun toItemList(itemListString: String): List<Food> {
        val gson = Gson()
        return gson.fromJson(itemListString, object : TypeToken<List<Food>>() {}.type)
    }
}