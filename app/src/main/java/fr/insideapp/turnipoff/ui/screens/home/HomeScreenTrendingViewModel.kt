package fr.insideapp.turnipoff.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.insideapp.turnipoff.model.search.MovieSearchResult
import fr.insideapp.turnipoff.network.TheMovieDBClient
import kotlinx.coroutines.launch

class HomeScreenTrendingViewModel : ViewModel() {
    var movieTrending = mutableStateListOf<MovieSearchResult>()

    var errorMessage: String by mutableStateOf("")

    init {
        getMovieTrendingList()
    }

    fun getMovieTrendingList() {
        viewModelScope.launch {
            try {
                val newMovies = TheMovieDBClient.service.discover(
                    page = 1
                ).body()?.results?.take(6) ?: listOf()

                movieTrending.addAll(newMovies)
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}