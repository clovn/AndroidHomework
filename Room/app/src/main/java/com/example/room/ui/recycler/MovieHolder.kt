package com.example.room.ui.recycler

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.room.data.entities.Movie
import com.example.room.databinding.ItemMovieBinding

class MovieHolder(
    private val binding: ItemMovieBinding,
    private val onShareButtonClicked: (Movie) -> Unit
): ViewHolder(binding.root) {

    fun onBind(movie: Movie){
        binding.apply {
            tvTitle.text = movie.title
            tvDirector.text = movie.director
            movie.year.toString().also { tvYear.text = it }
            tvDescription.text = movie.description
            movie.rating.toString().also { tvRating.text = it }

            shareBtn.setOnClickListener {
                onShareButtonClicked(movie)
            }
        }
    }
}