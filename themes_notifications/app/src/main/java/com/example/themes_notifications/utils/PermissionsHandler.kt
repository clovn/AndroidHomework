package com.example.themes_notifications.utils

import android.os.Build
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity


class PermissionsHandler(
    private val storagePermissionGranted: () -> Unit,
    private val notificationPermissionGranted: () -> Unit
) {
    private var storagePermissionResult: ActivityResultLauncher<String>? = null
    private var notificationsPermissionResult: ActivityResultLauncher<String>? = null

    fun initContracts(activity: AppCompatActivity){
        if(storagePermissionResult == null){
            storagePermissionResult = activity.registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                if(isGranted){
                    storagePermissionGranted()
                }
            }
        }

        if(notificationsPermissionResult == null){
            notificationsPermissionResult = activity.registerForActivityResult(ActivityResultContracts.RequestPermission()){ isGranted ->
                if(isGranted){
                    notificationPermissionGranted
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun requestNotificationsPermission(){
        notificationsPermissionResult?.launch(android.Manifest.permission.POST_NOTIFICATIONS)
    }

    fun requestStoragePermission(permission: String){
        storagePermissionResult?.launch(permission)
    }
}