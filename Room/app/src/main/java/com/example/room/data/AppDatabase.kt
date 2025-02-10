package com.example.room.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.room.data.dao.MovieDao
import com.example.room.data.dao.UserDao
import com.example.room.data.entities.Movie
import com.example.room.data.entities.User

@Database(entities = [User::class, Movie::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val movieDao: MovieDao
}