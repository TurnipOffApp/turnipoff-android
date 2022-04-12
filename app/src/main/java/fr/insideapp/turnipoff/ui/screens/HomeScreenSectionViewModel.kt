package fr.insideapp.turnipoff.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import fr.insideapp.turnipoff.model.TheMovieDBMovieGenre
import fr.insideapp.turnipoff.model.TheMovieDBResponse
import fr.insideapp.turnipoff.model.search.MovieSearchResult
import fr.insideapp.turnipoff.network.TheMovieDBClient
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

sealed class HomeScreenSectionDataHolder(
    val title: String,
    var page: Int = 0,
    val pageMax: Int = 1,
    val data: MutableList<MovieSearchResult> = mutableListOf()
) {
    class WorstAction(
        page: Int = 0,
        data: MutableList<MovieSearchResult> = mutableListOf()
    ) : HomeScreenSectionDataHolder(
        title = "Worst action movies",
        page = page,
        data = data
    )

    class Worst90(
        page: Int = 0,
        data: MutableList<MovieSearchResult> = mutableListOf()
    ) : HomeScreenSectionDataHolder(
        title = "Worst 90's movies",
        page = page,
        data = data
    )
}

class HomeScreenSectionViewModel() : ViewModel() {
    private val dateFormat = SimpleDateFormat("yyyy-dd-MM")

    var worstActionMovies: HomeScreenSectionDataHolder.WorstAction by mutableStateOf(HomeScreenSectionDataHolder.WorstAction())
    var worst90sMovies: HomeScreenSectionDataHolder.Worst90 by mutableStateOf(HomeScreenSectionDataHolder.Worst90())

    var errorMessage: String by mutableStateOf("")

    init {
        loadMore(worstActionMovies)
        loadMore(worst90sMovies)
    }

    fun loadMore(dataHolder: HomeScreenSectionDataHolder) {
        if(dataHolder.page < dataHolder.pageMax) {
            dataHolder.page += 1

            when(dataHolder) {
                is HomeScreenSectionDataHolder.WorstAction -> getWorstActionMovies(dataHolder)
                is HomeScreenSectionDataHolder.Worst90 -> getWorst90sMovies(dataHolder)
            }
        }
    }

    private fun getWorstActionMovies(dataHolder: HomeScreenSectionDataHolder) {
        viewModelScope.launch {
            try {
                val moviesResult = TheMovieDBClient.service.discover(
                    genres = mutableListOf(TheMovieDBMovieGenre.Action),
                    sortby = "vote_average.asc",
                    voteAverage = "1",
                    page = dataHolder.page
                ).body()

                if(moviesResult != null) {
                    val movies = moviesResult.results

                    val newDataHolder = HomeScreenSectionDataHolder.WorstAction(
                        page = moviesResult.page,
                        data = (dataHolder.data + movies).toMutableList()
                    )

                    worstActionMovies = newDataHolder
                }
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }

    private fun getWorst90sMovies(dataHolder: HomeScreenSectionDataHolder) {
        viewModelScope.launch {
            try {
                val moviesResult = TheMovieDBClient.service.discover(
                    sortby = "vote_average.asc",
                    voteAverage = "1",
                    page = dataHolder.page,
                    releaseAfter = "1990-01-01",
                    releaseBefore = "1999-12-31",
                ).body()

                if(moviesResult != null) {
                    val movies = moviesResult.results

                    val newDataHolder = HomeScreenSectionDataHolder.Worst90(
                        page = moviesResult.page,
                        data = (dataHolder.data + movies).toMutableList()
                    )

                    worst90sMovies = newDataHolder
                }
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}

/*class HomeScreenSectionViewModelFactory(private val type: HomeScreenSectionHolder.Type) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = HomeScreenSectionViewModel(type) as T
}*/