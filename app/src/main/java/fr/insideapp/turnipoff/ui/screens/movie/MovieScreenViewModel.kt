package fr.insideapp.turnipoff.ui.screens.movie

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import fr.insideapp.turnipoff.model.TheMovieDBMovieGenre
import fr.insideapp.turnipoff.model.movie.Movie
import fr.insideapp.turnipoff.model.movie.MovieCredits
import fr.insideapp.turnipoff.model.search.MovieSearchResult
import fr.insideapp.turnipoff.network.TheMovieDBClient
import fr.insideapp.turnipoff.ui.screens.home.HomeScreenSectionDataHolder
import kotlinx.coroutines.launch

class MovieScreenViewModel(val movieId: Long): ViewModel() {
    var movieDetails: Movie? by mutableStateOf(null)
    var movieCredits: MovieCredits? by mutableStateOf(null)

    init {
        refreshMovie()
        refreshMovieCredits()
    }

    fun refreshMovie() {
        viewModelScope.launch {
            val movieResult = TheMovieDBClient.service.getMovie(
                movieId = movieId
            ).body()

            movieDetails = movieResult
        }
    }

    fun refreshMovieCredits() {
        viewModelScope.launch {
            val movieCreditsResult = TheMovieDBClient.service.getMovieCredits(
                movieId = movieId
            ).body()

            movieCredits = movieCreditsResult
        }
    }
}

class MovieScreenViewModelFactory constructor(private val movieId: Long): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(MovieScreenViewModel::class.java)) {
            MovieScreenViewModel(movieId) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}