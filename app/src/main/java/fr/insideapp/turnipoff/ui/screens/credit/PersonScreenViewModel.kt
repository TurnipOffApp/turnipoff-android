package fr.insideapp.turnipoff.ui.screens.credit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import fr.insideapp.turnipoff.model.movie.MovieCredits
import fr.insideapp.turnipoff.model.person.Person
import fr.insideapp.turnipoff.network.TheMovieDBClient
import kotlinx.coroutines.launch

class PersonScreenViewModel(val personId: Long): ViewModel() {
    var personDetails: Person? by mutableStateOf(null)
    var personCredits: MovieCredits? by mutableStateOf(null)
    var personAverage: Double by mutableStateOf(0.0)

    init {
        refreshPerson()
        refreshPersonCredits()
    }

    fun refreshPerson() {
        viewModelScope.launch {
            val personResult = TheMovieDBClient.service.getPerson(
                personId = personId
            ).body()

            personDetails = personResult
        }
    }

    fun refreshPersonCredits() {
        viewModelScope.launch {
            val personCreditResult = TheMovieDBClient.service.getPersonCredits(
                personId = personId
            ).body()

            personCredits = personCreditResult
            personAverage = personCreditResult?.cast?.map { it.average }?.average() ?: 0.0
        }
    }
}

class PersonScreenViewModelFactory constructor(private val personId: Long): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(PersonScreenViewModel::class.java)) {
            PersonScreenViewModel(personId) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}