package fr.insideapp.turnipoff.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.pager.*
import fr.insideapp.turnipoff.model.search.MovieSearchResult
import fr.insideapp.turnipoff.network.PictureSizes
import fr.insideapp.turnipoff.ui.theme.Margin

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreenTrending(viewModel: HomeScreenViewModel, configuration: Configuration) {
    Column(
        modifier = Modifier
            .height((configuration.screenHeightDp * 0.3).dp),
        verticalArrangement = Arrangement.spacedBy(Margin.normal)
    ) {
        val pagerState = rememberPagerState()
        val movieTrending = viewModel.movieTrending

        HomeScreenTrendingList(
            pagerState = pagerState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            listTrending = movieTrending
        )

        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun HomeScreenTrendingList(pagerState: PagerState, modifier: Modifier, listTrending: List<MovieSearchResult>) {
    HorizontalPager(
        count = listTrending.count(),
        state = pagerState,
        modifier = modifier
    ) { page ->
        val posterPath = listTrending[page].posterPath

        if(posterPath != null) {
            Image(
                painter = rememberAsyncImagePainter(PictureSizes.Poster.W342.buildURL(posterPath)),
                contentDescription = null
            )
        }
    }
}