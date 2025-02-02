package com.example.themes_notifications.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.themes_notifications.Constants
import com.example.themes_notifications.MainActivity
import com.example.themes_notifications.R
import com.example.themes_notifications.model.NotificationData

class NotificationHandler(
    private val appCtx: Context
) {
    private val notificationManager = appCtx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    init {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createNotificationChannels()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannels(){
        Constants.notificationsChannels.forEach { notificationChannelData ->
            notificationManager.createNotificationChannel(NotificationChannel(
                notificationChannelData.channelId,
                notificationChannelData.name,
                notificationChannelData.importance
            ))
        }
    }

    fun showNotification(data: NotificationData){
        val intent = Intent(appCtx, MainActivity::class.java)
        intent.putExtra(Constants.EXTRA_NOTIFICATION, 1)
        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            appCtx,
            0,
            intent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )

        val notificationBuilder = NotificationCompat.Builder(appCtx, data.channelId)
            .setContentTitle(data.title)
            .setSmallIcon(R.drawable.ic_notifications)
            .setContentText(data.text)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)

        notificationManager.notify(data.id, notificationBuilder.build())
    }
}