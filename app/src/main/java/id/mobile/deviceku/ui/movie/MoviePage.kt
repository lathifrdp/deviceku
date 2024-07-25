package id.mobile.deviceku.ui.movie

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import id.mobile.deviceku.theme.DeviceKuTheme
import id.mobile.deviceku.util.formatDate

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MoviePage(viewModel: MovieViewModel, onFabClick: () -> Unit) {
    DeviceKuTheme {
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    modifier = Modifier.padding(bottom = 64.dp),
                    onClick = onFabClick,
                    containerColor = MaterialTheme.colorScheme.secondary,
                    shape = RoundedCornerShape(16.dp),
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Add,
                        contentDescription = "Add FAB",
                        tint = Color.White,
                    )
                }
            },
            content = { paddingValues ->
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .navigationBarsPadding(), color = MaterialTheme.colorScheme.background
                ) {
                    MovieScreen(viewModel)
                }
            },
        )
    }
}

@Composable
fun MovieScreen(viewModel: MovieViewModel) {
    val listMovie by viewModel.listMovie.observeAsState(emptyList())
    val uiState = viewModel.uiState.collectAsState()
    val listState = rememberLazyListState()

    LaunchedEffect(Unit) {
        viewModel.getListMovie()
    }

    Box(modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 64.dp)) {
        when (val state = uiState.value) {
            is MovieUiState.GetMovieLoading -> {
                Box(
                    contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize(),
                ) {
                    CircularProgressIndicator()
                }
            }

            is MovieUiState.GetMovieLoaded -> {
                Column {
                    if (listMovie.isEmpty()) {
                        Text(text = "Data Kosong")
                    } else {
                        // Display the list of data
                        LazyColumn(state = listState) {
                            items(listMovie) { data ->
                                Spacer(modifier = Modifier.padding(8.dp))
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp)
                                ) {
                                    val posterUrl = data.poster
                                    if (posterUrl != null) {
                                        AsyncImage(
                                            model = ImageRequest.Builder(LocalContext.current)
                                                .data(posterUrl)
                                                .crossfade(true)
                                                .build(),
                                            contentDescription = null,
                                            modifier = Modifier
                                                .size(100.dp)
                                                .padding(end = 8.dp)
                                                .clip(RoundedCornerShape(8.dp)),
                                            contentScale = ContentScale.Fit
                                        )
                                    } else {
                                        // Display a placeholder or default image
                                        Box(
                                            modifier = Modifier
                                                .size(100.dp)
                                                .padding(end = 8.dp)
                                                .clip(RoundedCornerShape(8.dp)) // Apply rounded corners
                                                .background(Color.Gray),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text("No Image")
                                        }
                                    }
                                    Column(
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Text(
                                            text = (data.title ?: "-"),
                                            style = MaterialTheme.typography.titleMedium,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Spacer(modifier = Modifier.padding(2.dp))
                                        Text(
                                            text = (data.description ?: "-"),
                                            style = MaterialTheme.typography.titleSmall,
                                        )
                                        Spacer(modifier = Modifier.padding(4.dp))
                                        Text(
                                            text = (formatDate(data.createdDate ?: "-")),
                                            style = MaterialTheme.typography.titleSmall,
                                            textAlign = TextAlign.End
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.padding(8.dp))
                                HorizontalDivider() // Add a divider between items
                            }

                            item {
                                if (viewModel.isLoading) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        CircularProgressIndicator()
                                    }
                                }
                            }
                        }
                    }
                }

                // Trigger loading more data when reaching the end of the list
                listState.OnEndReached {
                    viewModel.getListMovie()
                }
            }

            is MovieUiState.GetMovieError -> {
                Text(text = state.message)
            }
        }
    }
}

@Composable
fun LazyListState.OnEndReached(
    buffer: Int = 2,
    loadMore: () -> Unit
) {
    val shouldLoadMore = remember {
        derivedStateOf {
            val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()
            lastVisibleItem != null && lastVisibleItem.index >= layoutInfo.totalItemsCount - buffer
        }
    }

    LaunchedEffect(shouldLoadMore.value) {
        if (shouldLoadMore.value) {
            loadMore()
        }
    }
}

/*
Explanation:

    LazyListState:
        rememberLazyListState is used to track the scroll state of the LazyColumn.
        A custom OnEndReached composable extension function is created to detect when the user has scrolled to the end of the list.

    Loading Indicator:
        The loading indicator is added as the last item in the LazyColumn and displayed when viewModel.isLoading is true.

    Trigger Loading More Data:
        OnEndReached checks if the user has scrolled to the end of the list (with a buffer of 2 items).
        When the end is reached, loadMore function (which triggers viewModel.getListMovie()) is called.

This approach ensures that the loading indicator is displayed at the end of the list and that more data is loaded when the user scrolls to the bottom.
*/