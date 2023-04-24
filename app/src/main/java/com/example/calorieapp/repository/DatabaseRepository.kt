package com.example.calorieapp.repository

import com.example.calorieapp.data.CalorieDatabaseDao
import com.example.calorieapp.model.FoodList
import com.example.calorieapp.model.ReminderItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DatabaseRepository @Inject constructor(private val calorieDao: CalorieDatabaseDao) {

    suspend fun insertFoodList(foodList: FoodList) {
        calorieDao.insert(foodList)
    }

    fun getFoodList(): Flow<List<FoodList>> {
        return calorieDao.getCorrectFoodList()
    }
    suspend fun insertReminder(reminderItem: ReminderItem){
        calorieDao.insertReminder(reminder = reminderItem)
    }
    fun getReminders():Flow<List<ReminderItem>>{
        return calorieDao.getReminders()
    }

    fun deleteReminder(item: ReminderItem){
        calorieDao.Delete(item)
    }

}
