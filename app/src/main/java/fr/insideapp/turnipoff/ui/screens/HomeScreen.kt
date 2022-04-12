package fr.insideapp.turnipoff.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import fr.insideapp.turnipoff.ui.theme.Margin

@Composable
fun HomeScreen(navController: NavController) {
    val configuration = LocalConfiguration.current
    val viewModel: HomeScreenViewModel = viewModel()
    val scrollState = rememberScrollState()



    Column(
        verticalArrangement = Arrangement.spacedBy(Margin.medium)
    ) {
        HomeScreenTrending(
            viewModel = viewModel,
            configuration = configuration
        )
        Divider(
            modifier = Modifier.background(Color.Gray)
        )

        WorstActionMovies(navController)

        Divider(
            modifier = Modifier.background(Color.Gray)
        )

        Worst90Movies(navController)
    }

    viewModel.getMovieTrendingList()
    //viewModel.getWorstActionMovies(SectionWorstAction())
}

@Composable
fun WorstActionMovies(navController: NavController) {
    val viewModel: HomeScreenSectionViewModel = viewModel()
    val worstActionMovies = viewModel.worstActionMovies

    HomeScreenSection(worstActionMovies) {
        viewModel.loadMore(worstActionMovies)
    }
}

@Composable
fun Worst90Movies(navController: NavController) {
    val viewModel: HomeScreenSectionViewModel = viewModel()
    val worst90sMovies = viewModel.worst90sMovies

    HomeScreenSection(worst90sMovies) {
        viewModel.loadMore(worst90sMovies)
    }
}