package fr.insideapp.turnipoff.ui

import fr.insideapp.turnipoff.model.search.MovieSearchResult
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class Navigator {

    private val _sharedFlow = MutableSharedFlow<NavTarget>(extraBufferCapacity = 1)
    val sharedFlow = _sharedFlow.asSharedFlow()

    fun navigateTo(navTarget: NavTarget) {
        _sharedFlow.tryEmit(navTarget)
    }

    sealed class NavTarget(val label: NavTargetRoute) {
        object Home: NavTarget(NavTargetRoute.Home)
        class Movie(movieSearchResult: MovieSearchResult): NavTarget(NavTargetRoute.Movie)
        object Person: NavTarget(NavTargetRoute.Person)
    }

    enum class NavTargetRoute(val route: String) {
        Home("home"),
        Movie("movie/{name}/{id}"),
        Person("person/{name}/{id}");
    }
}