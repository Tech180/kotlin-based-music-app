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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.musicify.R

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.folder_regular),
            contentDescription = "Musicify Logo",
            modifier = Modifier.size(120.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Welcome to Musicify!")
        Text(text = "Discover and enjoy unlimited music")

        MusicPlayer()
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
