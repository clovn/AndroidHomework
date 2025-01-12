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
            color = Color.parseColor("#FF3B3B"),
        ),
        Theme(
            id = 2,
            color = Color.parseColor("#32FF7E"),
        ),
        Theme(
            id = 3,
            color = Color.parseColor("#FFEB3B"),
        )
    )

    const val EXTRA_THEME = "theme"

    const val EXTRA_NOTIFICATION = "notification"

    const val EMPTY_FIELDS = "Заполните все поля"

    const val IMAGE_UPLOADED = "Изображение уже загружено"

}