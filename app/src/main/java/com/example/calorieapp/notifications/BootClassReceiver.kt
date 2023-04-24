package com.example.calorieapp.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.calorieapp.repository.DatabaseRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@AndroidEntryPoint()
class BootClassReceiver: BroadcastReceiver() {
    @Inject lateinit var databaseRepository: DatabaseRepository
    override fun onReceive(context: Context, intent: Intent?) {
        if (intent?.action == Intent.ACTION_BOOT_COMPLETED) {
            CoroutineScope(Dispatchers.IO).launch {
                val reminders = databaseRepository.getReminders()
                reminders.collect {
                    it.forEach {
                        reminder ->
                        NotificationScheduler(context).schedule(reminder)
                    }
                }
            }
        }
    }
}