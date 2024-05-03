package com.example.musicify.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.musicify.R
import com.example.musicify.ui.component.CustomSearchBar
import com.example.musicify.ui.component.MediaCarousel
import com.example.musicify.ui.models.Cover

@Composable
fun HomeScreen() {
    var searchText by remember { mutableStateOf("") }


    //Temporary until artist, albums, songs, and music player are set up
    val mediaList = remember {
        listOf(
            Cover(0, "Title 1", "poster_1", "backdrop_1", "Overview 1"),
            Cover(1, "Title 2", "poster_2", "backdrop_2", "Overview 2"),
            Cover(2, "Title 3", "poster_3", "backdrop_3", "Overview 3"),
        )
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        CustomSearchBar(
            title = { Text("Search text") },
            searchText = searchText,
            onSearchTextChange = { searchText = it },
            onClearClick = { searchText = "" }
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            MediaCarousel(
                list = mediaList,
                totalItemsToShow = 10,
                onItemClicked = {
                    /* handle item click */
                }
            )

            Image(
                painter = painterResource(id = R.drawable.folder_regular),
                contentDescription = "Musicify Logo",
                modifier = Modifier.size(120.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Welcome to Musicify!")
            Text(text = "Discover and enjoy unlimited music")
            Spacer(modifier = Modifier.height(16.dp))
            MusicPlayer()
        }
    }
}


@Composable
fun MusicPlayer() {

    var isPlaying by remember { mutableStateOf(false) }
    var currentTrack by remember { mutableIntStateOf(0) }

    val songs = listOf(
        "Song 1",
        "Song 2",
        "Song 3"
    )

    // Music Player Controls
    Spacer(modifier = Modifier.height(32.dp))
    Button(onClick = { isPlaying = !isPlaying }) {
        Text(text = if (isPlaying) "Pause" else "Play")
    }
    Spacer(modifier = Modifier.height(8.dp))
    Button(onClick = {
        currentTrack = (currentTrack + 1) % songs.size
    }) {
        Text(text = "Next Track")
    }

    // Currently Playing Song Information
    Spacer(modifier = Modifier.height(32.dp))
    Text(text = "Currently Playing:")
    Text(text = "Song Title") // Replace "Song Title" with actual song title
    Text(text = "Artist Name") // Replace "Artist Name" with actual artist name
}
