package com.example.room

import android.app.Application
import androidx.room.Room
import com.example.room.data.AppDatabase
import com.example.room.utils.SessionManager

class App : Application() {

    private var db: AppDatabase? = null
    private var sessionManager: SessionManager? = null

    private val DATABASE_NAME = "room"

    override fun onCreate() {
        super.onCreate()

        if(db == null){
            db = Room.databaseBuilder(this, AppDatabase::class.java, DATABASE_NAME)
                .build()
        }

        if(sessionManager == null){
            sessionManager = SessionManager(this)
        }
    }

    fun getDatabase(): AppDatabase {
        return db ?: throw IllegalStateException("Database not initialized")
    }

    fun getSessionManager(): SessionManager {
        return sessionManager ?: throw IllegalStateException("SessionManager not initialized")
    }


}