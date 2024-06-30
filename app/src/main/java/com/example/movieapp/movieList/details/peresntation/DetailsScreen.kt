package com.example.movieapp.movieList.details.peresntation

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ImageNotSupported
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.movieapp.R
import com.example.movieapp.movieList.data.remote.MovieApi
import com.example.movieapp.movieList.util.RatingBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(navController: NavController, backStackEntry: NavBackStackEntry) {

    val detailsViewModel = hiltViewModel<DetailsViewModel>()
    val detailsState = detailsViewModel.detailsState.collectAsState().value

    val backDropImageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(MovieApi.IMAGE_BASE_URL + detailsState.movie?.backdrop_path)
            .size(Size.ORIGINAL)
            .build()
    ).state

    val posterImageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(MovieApi.IMAGE_BASE_URL + detailsState.movie?.poster_path)
            .size(Size.ORIGINAL)
            .build()
    ).state

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier.fillMaxSize().nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = detailsState.movie?.title ?: stringResource(R.string.loading))
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                        contentDescription = stringResource(R.string.back),
                        modifier = Modifier.clickable {
                            navController.navigateUp()
                        }
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                scrollBehavior = scrollBehavior
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {

                if (backDropImageState is AsyncImagePainter.State.Error) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp)
                            .background(MaterialTheme.colorScheme.primaryContainer),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            modifier = Modifier.size(70.dp),
                            imageVector = Icons.Rounded.ImageNotSupported,
                            contentDescription = detailsState.movie?.title ?: ""
                        )
                    }
                }

                if (backDropImageState is AsyncImagePainter.State.Success) {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp),
                        painter = backDropImageState.painter,
                        contentDescription = detailsState.movie?.title ?: "",
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .width(160.dp)
                            .height(240.dp)
                    ) {
                        if (posterImageState is AsyncImagePainter.State.Error) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(MaterialTheme.colorScheme.primaryContainer),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    modifier = Modifier.size(70.dp),
                                    imageVector = Icons.Rounded.ImageNotSupported,
                                    contentDescription = detailsState.movie?.title ?: ""
                                )
                            }
                        }

                        if (posterImageState is AsyncImagePainter.State.Success) {
                            Image(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(RoundedCornerShape(12.dp)),
                                painter = posterImageState.painter,
                                contentDescription = detailsState.movie?.title ?: "",
                                contentScale = ContentScale.Crop
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    detailsState.movie?.let { movie ->
                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                modifier = Modifier.padding(top = 16.dp),
                                text = movie.title ?: "",
                                fontSize = 19.sp,
                                fontWeight = FontWeight.SemiBold
                            )

                            Text(
                                modifier = Modifier.padding(top = 14.dp),
                                text = (stringResource(R.string.original_title) + movie.original_title) ?: "",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.LightGray
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Text(
                                text = stringResource(R.string.language) + movie.original_language,
                                fontSize = 14.sp,
                                color = Color.LightGray,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )

                            Text(
                                text = stringResource(R.string.release_date) + movie.release_date,
                                fontSize = 14.sp,
                                color = Color.LightGray,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )

                            Text(
                                text = stringResource(R.string.votes) + movie.vote_count.toString(),
                                fontSize = 14.sp,
                                color = Color.LightGray,
                            )
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(bottom = 8.dp)
                            ) {
                                Text(
                                    text = stringResource(R.string.rating),
                                    color = Color.LightGray,
                                    fontSize = 14.sp,
                                )
                                Spacer(modifier = Modifier.width(4.dp))

                                RatingBar(
                                    starsModifier = Modifier.size(18.dp),
                                    rating = movie.vote_average / 2
                                )

                                Spacer(modifier = Modifier.width(4.dp))

                                Text(
                                    text = movie.vote_average.toString().take(3),
                                    color = Color.LightGray,
                                    fontSize = 14.sp,
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = stringResource(R.string.overview),
                    fontSize = 19.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
                )

                detailsState.movie?.let {
                    Text(
                        text = it.overview ?: "",
                        fontSize = 16.sp,
                        modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                        color = Color.LightGray,
                        textAlign = TextAlign.Justify
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    )
}
