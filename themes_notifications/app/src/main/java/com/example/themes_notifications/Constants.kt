package com.example.themes_notifications

import android.app.NotificationManager
import android.graphics.Color
import com.example.themes_notifications.model.NotificationChannelData
import com.example.themes_notifications.model.Theme

object Constants {
    val notificationsChannels = listOf(
        NotificationChannelData("low_channel_id", "LOW", NotificationManager.IMPORTANCE_LOW),
        NotificationChannelData("default_channel_id", "DEFAULT", NotificationManager.IMPORTANCE_DEFAULT),
        NotificationChannelData("high_channel_id", "HIGH", NotificationManager.IMPORTANCE_HIGH),
        NotificationChannelData("max_channel_id", "MAX", NotificationManager.IMPORTANCE_MAX),
    )

    val themes = listOf(
        Theme(
            id = 1,
            color = R.color.red_color,
        ),
        Theme(
            id = 2,
            color = R.color.yellow_color,
        ),
        Theme(
            id = 3,
            color = R.color.green_color,
        )
    )

    const val EXTRA_THEME = "theme"

    const val EXTRA_NOTIFICATION = "notification"

    const val EMPTY_FIELDS = "Заполните все поля"

    const val IMAGE_UPLOADED = "Изображение уже загружено"

}