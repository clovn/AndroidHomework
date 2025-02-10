package com.example.room.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.room.data.entities.Movie

@Dao
interface MovieDao {
    @Insert
    suspend fun insertMovie(movie: Movie)

    @Query("SELECT * FROM movies WHERE user_id = :userId")
    suspend fun getMovies(userId: Int): MutableList<Movie>

    @Delete
    suspend fun deleteMovie(movie: Movie)
}
