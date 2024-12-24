package com.example.viewpager

data class Question(
    val id : Int,
    val text : String,
    val answers : List<String>,
    var selectedAnswer : Int = -1
)
