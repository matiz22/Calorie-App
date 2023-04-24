package com.example.calorieapp.notifications

import com.example.calorieapp.model.ReminderItem

interface AlarmScheduler {
    fun schedule(item: ReminderItem)
    fun cancel(item: ReminderItem)
}