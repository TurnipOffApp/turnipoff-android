package fr.insideapp.turnipoff.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import fr.insideapp.turnipoff.R
import fr.insideapp.turnipoff.network.PictureSizes
import fr.insideapp.turnipoff.ui.theme.Margin
import fr.insideapp.turnipoff.ui.utils.OnEndReached

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreenSection(holder: HomeScreenSectionDataHolder, onLoadMore: (HomeScreenSectionDataHolder) -> Unit) {
    val listState = rememberLazyListState()

    Column(
        verticalArrangement = Arrangement.spacedBy(Margin.medium)
    ) {
        Text(
            text = holder.title,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Margin.normal)
        )
        LazyRow(
            modifier = Modifier.wrapContentHeight(),
            contentPadding = PaddingValues(horizontal = Margin.normal),
            horizontalArrangement = Arrangement.spacedBy(Margin.normal),
            state = listState
        ) {
            items(holder.data) { item ->
                val posterPath = item.posterPath

                Card(
                    backgroundColor = Color(0xFFFF0800),
                    modifier = Modifier
                        .width(80.dp)
                        .height(120.dp),
                    elevation = 8.dp,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    if(posterPath != null) {
                        Image(
                            painter = rememberAsyncImagePainter(
                                model = PictureSizes.Poster.W342.buildURL(posterPath),
                                placeholder = painterResource(id = R.drawable.missing_picture)
                            ),
                            contentScale = ContentScale.Crop,
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize()
                        )
                    } else {
                        Image(
                            painter = painterResource(id = R.drawable.missing_picture),
                            contentScale = ContentScale.Crop,
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }

            }
        }
    }

    listState.OnEndReached {
        onLoadMore(holder)
    }
}
