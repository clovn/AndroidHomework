package com.example.room.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.room.data.entities.User

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User)

    @Query("SELECT id FROM users WHERE username = :username AND password = :password")
    suspend fun login(username: String, password: String): Int?

    @Query("SELECT * FROM users WHERE username = :username")
    suspend fun checkUserExists(username: String): User?
}