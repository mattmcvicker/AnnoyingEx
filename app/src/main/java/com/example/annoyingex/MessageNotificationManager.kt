package com.example.annoyingex

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlin.random.Random

class MessageNotificationManager(private val context: Context) {
    private val notificationManagerCompat = NotificationManagerCompat.from(context)

    init {
        createKarenChannel()
    }

    fun postNotification() {

        val karenIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        //karenIntent.putExtra("p", "cookie")
        val pendingKarenIntent = PendingIntent.getActivity(context, 1234, karenIntent, 0)
        val yeet = context.applicationContext as MessageApplication
        val message = yeet.randomString
        val notification = NotificationCompat.Builder(context, KAREN_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Karen")
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setContentIntent(pendingKarenIntent)
            .setAutoCancel(true)
            .build()

        notificationManagerCompat.notify(Random.nextInt(), notification)
    }

    private fun createKarenChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Notifications Ex"
            val descriptionText = "All messages from Karen"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(KAREN_CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            notificationManagerCompat.createNotificationChannel(channel)
        }
    }

    companion object {
        const val KAREN_CHANNEL_ID = "WHYKAREN"
    }
}
