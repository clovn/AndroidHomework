package com.example.themes_notifications

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.themes_notifications.utils.NotificationHandler

class MainActivity : AppCompatActivity(R.layout.activity_main) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent.getIntExtra(Constants.EXTRA_NOTIFICATION, -1).let{
            if(it == 1){
                Toast.makeText(
                    this,
                    "Запущено из уведомления",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        val theme = intent.getIntExtra(Constants.EXTRA_THEME, 0)

        when(theme){
            1 -> setTheme(R.style.Theme_Themes_notifications_Red)
            2 -> setTheme(R.style.Theme_Themes_notifications_Green)
            3 -> setTheme(R.style.Theme_Themes_notifications_Yellow)
            else -> setTheme(R.style.Theme_Themes_notifications)
        }

        supportFragmentManager.beginTransaction()
            .add(R.id.container, ThemeNotificationFragment())
            .commit()

    }
}