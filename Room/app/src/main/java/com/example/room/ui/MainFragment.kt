package com.example.room.ui

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.room.App
import com.example.room.R
import com.example.room.data.repository.MovieRepository
import com.example.room.databinding.FragmentMainBinding
import com.example.room.ui.recycler.MoviesAdapter
import com.example.room.ui.recycler.SwipeToDeleteCallback
import com.example.room.utils.SessionManager
import kotlinx.coroutines.launch

class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding: FragmentMainBinding by viewBinding()
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

    private fun initRecycler(){
        lifecycleScope.launch {
            sessionManager?.let {session ->
                movieRepository?.let {
                    val items = it.getUserMovies(session.getUserId())

                    binding.apply {
                        rvMovies.adapter = MoviesAdapter(
                            items = items,
                            onDelete = {movie ->
                                lifecycleScope.launch {
                                    movieRepository?.deleteMovie(movie)
                                }
                            },
                            onShareButtonClicked = {movie ->
                                val intent = Intent(Intent.ACTION_SEND)
                                intent.type = "text/html"

                                val message = getString(
                                    R.string.message_html,
                                    movie.title,
                                    movie.director,
                                    movie.description,
                                    movie.year.toString(),
                                    movie.rating.toString()
                                )

                                intent.putExtra(Intent.EXTRA_SUBJECT,
                                    getString(R.string.recommendation))
                                intent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(message, Html.FROM_HTML_MODE_LEGACY))
                                startActivity(intent)
                            }
                        )
                        rvMovies.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

                        ItemTouchHelper(SwipeToDeleteCallback { position ->
                            (rvMovies.adapter as MoviesAdapter).deleteItem(position)
                        }).attachToRecyclerView(rvMovies)
                    }
                }
            }
        }
    }

    private fun initViews(){
        binding.apply {
            logout.setOnClickListener{
                findNavController().navigate(R.id.action_mainFragment_to_loginFragment)
                sessionManager?.logoutUser()
            }

            addBtn.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_addMovieFragment)
            }
        }

        initRecycler()
    }

    override fun onDestroy() {
        super.onDestroy()
        movieRepository = null
        sessionManager = null
    }
}