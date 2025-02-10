package com.example.room.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.room.App
import com.example.room.R
import com.example.room.data.entities.Movie
import com.example.room.data.repository.MovieRepository
import com.example.room.databinding.FragmentAddMovieBinding
import com.example.room.utils.SessionManager
import kotlinx.coroutines.launch

class AddMovieFragment : Fragment(R.layout.fragment_add_movie) {

    private val binding: FragmentAddMovieBinding by viewBinding()
    private var movieRepository: MovieRepository? = null
    private var sessionManager: SessionManager? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity().application as? App)?.let {
            movieRepository = MovieRepository(movieDao = it.getDatabase().movieDao)
            sessionManager = it.getSessionManager()
        }

        initViews()
    }

    private fun initViews(){
        binding.apply {
            exitBtn.setOnClickListener {
                findNavController().popBackStack()
            }

            saveMovieButton.setOnClickListener{
                val title = movieTitle.text.toString()
                val director = movieDirector.text.toString()
                val year = movieYear.text.toString()
                val description = movieDescription.text.toString()
                val rating = movieRating.text.toString()

                if(title.isNotEmpty() && director.isNotEmpty() && year.isNotEmpty() && description.isNotEmpty() && rating.isNotEmpty()){
                    lifecycleScope.launch {
                        sessionManager?.let{
                            val movie = Movie(
                                userId = it.getUserId(),
                                title = title,
                                director = director,
                                description = description,
                                year = year.toInt(),
                                rating = rating.toDouble()
                            )

                            movieRepository?.insertMovie(movie)

                            findNavController().navigate(R.id.action_addMovieFragment_to_mainFragment)
                        }
                    }
                } else {
                    Toast.makeText(requireContext(), getString(R.string.fill_all_fields), Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        movieRepository = null
        sessionManager = null
    }
}