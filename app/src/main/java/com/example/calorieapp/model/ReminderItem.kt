package com.example.calorieapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reminders")
data class ReminderItem(
    @PrimaryKey
    val id: String,

)
