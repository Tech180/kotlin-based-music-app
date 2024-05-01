package com.example.musicify.titleScreen

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.musicify.ui.menu.DrawerActivity
import kotlinx.coroutines.launch
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.rememberImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.example.musicify.R


class TitleScreen : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MusicifyTitleScreen()
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MusicifyTitleScreen() {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val imageLoader = ImageLoader.Builder(context).components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }.build()

    Scaffold(
        content = { paddingValues ->
            Content(
                modifier = Modifier.padding(paddingValues).background(color = MaterialTheme.colorScheme.background),
                onClickStart = {
                    coroutineScope.launch {
                        navigateToDrawer(context)
                    }
                },
                imageLoader = imageLoader
            )
        }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Content(
    modifier: Modifier = Modifier,
    onClickStart: () -> Unit,
    imageLoader: ImageLoader
) {
    val robotoBold = FontFamily(Font(R.font.robotobold))

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title
        Text(
            text = "Musicify",
            style = androidx.compose.ui.text.TextStyle(
                fontFamily = robotoBold,
                fontSize = 32.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(vertical = 20.dp)
        )

        // Animated GIF Image
        val painter = rememberImagePainter (
            data = R.drawable.musicdark,
            imageLoader = imageLoader,
            builder = {
                // Disable hardware acceleration to avoid potential issues with some GIFs
                allowHardware(false)
            }
        )
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
                .padding(16.dp)
                .padding(16.dp)
        )

        // Start Button
        Spacer(modifier = Modifier.height(320.dp))
        TitleButton (
            text = "Start",
            onClick = onClickStart
        )
    }
}

@Composable
fun TitleButton(text: String, onClick: () -> Unit) {
    OutlinedButton(
        onClick = onClick,
        shape = RoundedCornerShape(50),
        modifier = Modifier
            .width(220.dp)
            .height(50.dp),
    ) {
        Text(
            text = text,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.primary
        )
    }
}
fun navigateToDrawer(context: Context) {
    val intent = Intent(context, DrawerActivity::class.java)
    context.startActivity(intent)
}
