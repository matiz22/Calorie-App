package com.example.calorieapp.data

import androidx.room.*
import com.example.calorieapp.model.FoodList
import com.example.calorieapp.model.ReminderItem
import kotlinx.coroutines.flow.Flow

@Dao
interface CalorieDatabaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(foodList: FoodList)

    @Query("SELECT * FROM food_list")
    fun getCorrectFoodList(): Flow<List<FoodList>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReminder(reminder: ReminderItem)

    @Query("SELECT * FROM reminders")
    fun getReminders():Flow<List<ReminderItem>>

    @Delete
    fun Delete(reminder: ReminderItem)
}
