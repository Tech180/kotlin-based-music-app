package com.example.musicify.ui.component

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import coil.compose.AsyncImage
import com.example.musicify.R
import com.example.musicify.ui.models.Cover
import com.example.musicify.ui.theme.TranslucentBlack
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@OptIn(
    ExperimentalFoundationApi::class
)
@Composable
fun MediaCarousel (
    list: List<Cover>,
    totalItemsToShow: Int = 10,
    carouselLabel: String = "",
    autoScrollDuration: Long = 1500L,
    onItemClicked: (Cover) -> Unit
) {
    //val pageCount = list.itemCount.coerceAtMost(totalItemsToShow)
    val pageCount = list.size.coerceAtMost(totalItemsToShow)

    val pagerState: PagerState = rememberPagerState(pageCount = { pageCount })

    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()

    if (isDragged.not()) {
        with(pagerState) {
            if (pageCount > 0) {
                var currentPageKey by remember {
                    mutableIntStateOf(0)
                }
                LaunchedEffect (key1 = currentPageKey) {
                    launch {
                        delay(timeMillis = autoScrollDuration)

                        val nextPage = (currentPage + 1).mod(pageCount)

                        animateScrollToPage (
                            page = nextPage,
                            animationSpec = tween(
                                durationMillis = 800
                            )
                        )

                        currentPageKey = nextPage
                    }
                }
            }
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box {
            HorizontalPager (
                state = pagerState,
                contentPadding = PaddingValues(
                    horizontal = 32.dp
                ),
                pageSpacing = 16.dp
            ) { page: Int ->
                val item: Cover? = list[page]
                item?.let {
                    Card (
                        onClick = { onItemClicked(it) },
                        modifier = Modifier.carouselTransition (
                            page,
                            pagerState
                        )
                    ) {
                        CarouselBox(it)
                    }
                }
            }
        }

        if (carouselLabel.isNotBlank()) {
            Text(
                text = carouselLabel,
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

//FIXME
fun String.toFullImageUrl(): String = "https://image.tmdb.org/t/p/w780/" + this

@Composable
fun CarouselBox(item: Cover) {
    Box {
        AsyncImage (
            model = item.backdropPath.toFullImageUrl(),
            contentDescription = null,
            placeholder = painterResource(id = R.drawable.ic_load_placeholder),
            error = painterResource(id = R.drawable.ic_load_placeholder),
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .height(
                    210.dp
                )
                .fillMaxWidth()
        )
        val gradient = remember {
            Brush.verticalGradient(
                listOf(
                    Color.Transparent,
                    TranslucentBlack
                )
            )
        }

        Text(
            text = item.name,
            color = Color.White,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(gradient)
                .padding(
                    horizontal = 16.dp,
                    vertical = 4.dp
                )
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
fun Modifier.carouselTransition (
    page: Int,
    pagerState: PagerState
) = graphicsLayer {
    val pageOffset =
        ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue

    val transformation = lerp (
        start = 0.8f,
        stop = 1f,
        fraction = 1f - pageOffset.coerceIn(
            0f,
            1f
        )
    )
    alpha = transformation
    scaleY = transformation
}