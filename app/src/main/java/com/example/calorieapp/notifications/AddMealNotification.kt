package com.example.calorieapp.notifications

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.compose.runtime.Composable
import androidx.core.app.NotificationCompat
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkBuilder
import androidx.navigation.NavGraph
import androidx.navigation.compose.rememberNavController
import com.example.calorieapp.MainActivity
import com.example.calorieapp.R
import com.example.calorieapp.util.Constants


class AddMealNotification(
    private val context: Context,
) {
    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun showNotification() {
        val notifyIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent =
            PendingIntent.getActivity(context, 0, notifyIntent, PendingIntent.FLAG_IMMUTABLE)
        val notification = NotificationCompat.Builder(context, Constants.channelID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Hey have you forgotten to add meal?")
            .setContentText("Jump in to application").setContentIntent(pendingIntent).build()
        notificationManager.notify(1, notification)
    }
}