package fr.insideapp.turnipoff.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.insideapp.turnipoff.model.TheMovieDBMediaType
import fr.insideapp.turnipoff.model.TheMovieDBMovieGenre
import fr.insideapp.turnipoff.model.TheMovieDBTimeWindow
import fr.insideapp.turnipoff.model.search.MovieSearchResult
import fr.insideapp.turnipoff.network.TheMovieDBClient
import kotlinx.coroutines.launch

class HomeScreenViewModel : ViewModel() {
    var movieTrending: List<MovieSearchResult> by mutableStateOf(listOf())

    var errorMessage: String by mutableStateOf("")

    fun getMovieTrendingList() {
        viewModelScope.launch {
            try {
                movieTrending = TheMovieDBClient.service.trending(
                    mediaType = TheMovieDBMediaType.Movie,
                    timeWindow = TheMovieDBTimeWindow.Week
                ).body()?.results ?: listOf()
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}