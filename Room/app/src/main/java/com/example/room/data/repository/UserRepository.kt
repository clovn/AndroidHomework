package com.example.room.data.repository

import com.example.room.data.dao.UserDao
import com.example.room.data.entities.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(
    private val userDao: UserDao
) {

    suspend fun checkUserExists(username: String): Boolean {
        return  withContext(Dispatchers.IO){
            userDao.checkUserExists(username) != null
        }
    }

    suspend fun insertUser(user: User) {
        withContext(Dispatchers.IO){
            userDao.insertUser(user)
        }
    }

    suspend fun login(username: String, password: String): Int {
        return withContext(Dispatchers.IO){
            userDao.login(username, password) ?: -1
        }
    }
}