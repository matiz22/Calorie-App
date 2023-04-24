package com.example.calorieapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

import androidx.room.TypeConverters
import com.example.calorieapp.util.DateConverter
import com.example.calorieapp.util.FoodListConverter
import java.util.*


@Entity(tableName = "food_list")
data class FoodList(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    var name: String = "",
    @TypeConverters(FoodListConverter::class)
    val items: MutableList<Food>,
    @TypeConverters(DateConverter::class)
    var date: Date = Date()
)