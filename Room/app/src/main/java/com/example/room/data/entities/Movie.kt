package com.example.room.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "user_id")
    val userId: Int,
    val title: String,
    val director: String,
    val description: String,
    val year: Int,
    val rating: Double
)