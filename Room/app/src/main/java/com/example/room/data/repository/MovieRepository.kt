package com.example.room.data.repository

import com.example.room.data.dao.MovieDao
import com.example.room.data.entities.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepository(
    private val movieDao: MovieDao
) {
    suspend fun getUserMovies(userId: Int): MutableList<Movie>{
        return withContext(Dispatchers.IO){
            movieDao.getMovies(userId)
        }
    }

    suspend fun insertMovie(movie: Movie) {
        withContext(Dispatchers.IO){
            movieDao.insertMovie(movie)
        }
    }

    suspend fun deleteMovie(movie: Movie) {
        withContext(Dispatchers.IO){
            movieDao.deleteMovie(movie)
        }
    }
}