package com.example.calorieapp.notifications

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import androidx.navigation.NavGraph
import androidx.navigation.NavHost
import com.example.calorieapp.model.ReminderItem

class NotificationScheduler(private val context: Context) : AlarmScheduler {

    private val alarmManager = context.getSystemService(AlarmManager::class.java)
    override fun schedule(item: ReminderItem) {
        val intent = Intent(context, AlarmReceiver::class.java)
        val timeOff9: Calendar = Calendar.getInstance()
        timeOff9.set(Calendar.HOUR_OF_DAY, item.id.split(':')[0].toInt())
        timeOff9.set(Calendar.MINUTE, item.id.split(':')[1].toInt())
        timeOff9.set(Calendar.SECOND, 0)
        alarmManager.setAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP, timeOff9.timeInMillis, PendingIntent.getBroadcast(
                context,
                item.hashCode(),
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )
    }

    override fun cancel(item: ReminderItem) {
        alarmManager.cancel(
            PendingIntent.getBroadcast(
                context,
                item.hashCode(),
                Intent(context, AlarmReceiver::class.java),
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )
    }


}