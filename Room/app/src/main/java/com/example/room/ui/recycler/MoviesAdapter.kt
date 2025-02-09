package com.example.room.ui.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.room.data.entities.Movie
import com.example.room.databinding.ItemMovieBinding

class MoviesAdapter(
    private val items: MutableList<Movie>,
    private val onDelete: (Movie) -> Unit,
    private val onShareButtonClicked: (Movie) -> Unit
): RecyclerView.Adapter<MovieHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder = MovieHolder(
        binding = ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        onShareButtonClicked = onShareButtonClicked
    )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.onBind(items[position])
    }

    fun deleteItem(position: Int){
        onDelete(items[position])

        items.removeAt(position)
        notifyItemRemoved(position)
    }

}