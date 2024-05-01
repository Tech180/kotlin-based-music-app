package com.example.musicify.musicPlayer

//import com.example.musicify.ui.screens.MusicScreen
import android.content.ContentResolver
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathOperation
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.media3.exoplayer.ExoPlayer
import com.example.musicify.R
import com.example.musicify.ui.theme.MusicifyTheme

class Music : ComponentActivity() {

    private lateinit var player: ExoPlayer

    data class Music (
        val name: String,
        val artist: String,
        val music: String
        //val cover: Int
    )

    object Playlist {
        fun getPlayList(contentResolver: ContentResolver): List<Music> {
            val projection = arrayOf(
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DATA // URI to audio file
            )
            val selection = "${MediaStore.Audio.Media.IS_MUSIC} != 0"
            val cursor = contentResolver.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                selection,
                null,
                null
            )
            val musicList = mutableListOf<Music>()

            cursor?.use { cursor ->
                val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)
                val titleColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)
                val artistColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)
                val uriColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)

                while (cursor.moveToNext()) {
                    val id = cursor.getLong(idColumn)
                    val title = cursor.getString(titleColumn)
                    val artist = cursor.getString(artistColumn)
                    val uri = cursor.getString(uriColumn)

                    musicList.add(Music(title, artist, uri))
                }
            }

            return musicList
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        player = ExoPlayer.Builder(this).build()
        val playList = Playlist.getPlayList(contentResolver)

        setContent {
            MusicifyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = colorScheme.background
                ) {
                    //MusicScreen(playList, player)
                }
            }
        }
    }

    @Composable
    private fun getPlayList(contentResolver: ContentResolver): List<Music> {
        // Query for audio files using MediaStore API
        val musicList = remember {
            val projection = arrayOf(
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DATA // URI to audio file
            )
            val selection = "${MediaStore.Audio.Media.IS_MUSIC} != 0"
            val cursor = contentResolver.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                selection,
                null,
                null
            )
            val musicList = mutableListOf<Music>()

            cursor?.use { cursor ->
                val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)
                val titleColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)
                val artistColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)
                val uriColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)

                while (cursor.moveToNext()) {
                    val id = cursor.getLong(idColumn)
                    val title = cursor.getString(titleColumn)
                    val artist = cursor.getString(artistColumn)
                    val uri = cursor.getString(uriColumn)

                    // For simplicity, we'll use a placeholder cover image
                    //val cover = R.drawable.placeholder_cover

                    // Add the music to the list
                    musicList.add(Music(title, artist, uri))
                }
            }

            musicList
        }

        return musicList
    }

    @Composable
    fun VinylAlbumCoverAnimation(
        modifier: Modifier = Modifier,
        isSongPlaying: Boolean = true,
        painter: Painter
    ) {
        var currentRotation by remember {
            mutableFloatStateOf(0f)
        }

        val rotation = remember {
            androidx.compose.animation.core.Animatable(currentRotation)
        }

        LaunchedEffect(isSongPlaying) {
            if (isSongPlaying) {
                rotation.animateTo(
                    targetValue = currentRotation + 360f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(3000, easing = LinearEasing),
                        repeatMode = RepeatMode.Restart
                    )
                ) {
                    currentRotation = value
                }
            } else {
                if (currentRotation > 0f) {
                    rotation.animateTo(
                        targetValue = currentRotation + 50,
                        animationSpec = tween(
                            1250,
                            easing = LinearOutSlowInEasing
                        )
                    ) {
                        currentRotation = value
                    }
                }
            }
        }

        VinylAlbumCover(
            painter = painter,
            rotationDegrees = rotation.value
        )
    }

    @Composable
    fun VinylAlbumCover(
        modifier: Modifier = Modifier,
        rotationDegrees: Float = 0f,
        painter: Painter
    ) {

        /**
         * Creates a custom outline for a rounded shape
         */
        val roundedShape = object : Shape {
            override fun createOutline(
                size: Size,
                layoutDirection: LayoutDirection,
                density: Density
            ): Outline {
                val p1 = Path().apply {
                    addOval(Rect(4f, 3f, size.width - 1, size.height - 1))
                }
                val thickness = size.height / 2.10f
                val p2 = Path().apply {
                    addOval(
                        Rect(
                            thickness,
                            thickness,
                            size.width - thickness,
                            size.height - thickness
                        )
                    )
                }
                val p3 = Path()
                p3.op(p1, p2, PathOperation.Difference)

                return Outline.Generic(p3)
            }
        }

        /**
         * Container defining the layout for a vinyl-themed UI element.
         */
        Box(
            modifier = modifier
                .aspectRatio(1.0f)
                .clip(roundedShape)
        ) {

            /**
             * Vinyl background image
             */
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .rotate(rotationDegrees),
                //painter = painterResource(id = R.drawable.vinyl_background),
                painter = painterResource(id = R.drawable.gear_solid),
                contentDescription = "Vinyl Background"
            )


            /**
             * Song album cover image overlaid on the vinyl background image
             */
            Image(
                modifier = Modifier
                    .fillMaxSize(0.5f)
                    .rotate(rotationDegrees)
                    .aspectRatio(1.0f)
                    .align(Alignment.Center)
                    .clip(roundedShape),
                painter = painter,
                contentDescription = "Song cover"
            )
        }
    }

    /**
     * Tracks and visualizes the song playing actions.
     */
    @Composable
    fun TrackSlider (
        value: Float,
        onValueChange: (newValue: Float) -> Unit,
        onValueChangeFinished: () -> Unit,
        songDuration: Float
    ) {
        Slider(
            value = value,
            onValueChange = {
                onValueChange(it)
            },
            onValueChangeFinished = {

                onValueChangeFinished()

            },
            valueRange = 0f..songDuration,
            colors = SliderDefaults.colors(
                thumbColor = Color.Black,
                activeTrackColor = Color.DarkGray,
                inactiveTrackColor = Color.Gray,
            )
        )
    }

    /*
    /***
     * Player control button
     */
    @Composable
    fun ControlButton(icon: Int, size: Dp, onClick: () -> Unit) {
        Box(
            modifier = Modifier
                .size(size)
                .clip(CircleShape)
                .clickable {
                    onClick()
                }, contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.size(size / 1.5f),
                painter = painterResource(id = icon),
                tint = Color.Black,
                contentDescription = null
            )
        }
    }

     */

    private fun Long.convertToText(): String {
        val sec = this / 1000
        val minutes = sec / 60
        val seconds = sec % 60

        val minutesString = if (minutes < 10) {
            "0$minutes"
        } else {
            minutes.toString()
        }
        val secondsString = if (seconds < 10) {
            "0$seconds"
        } else {
            seconds.toString()
        }
        return "$minutesString:$secondsString"
    }


}