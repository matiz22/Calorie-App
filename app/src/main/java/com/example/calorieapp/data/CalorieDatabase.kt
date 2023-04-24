package com.example.calorieapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.calorieapp.model.FoodList
import com.example.calorieapp.model.ReminderItem
import com.example.calorieapp.util.DateConverter
import com.example.calorieapp.util.FoodListConverter

@Database(entities = [FoodList::class, ReminderItem::class], version = 1, exportSchema = false)
@TypeConverters(FoodListConverter::class, DateConverter::class)
abstract class CalorieDatabase: RoomDatabase(){
    abstract fun calorieDao(): CalorieDatabaseDao
}