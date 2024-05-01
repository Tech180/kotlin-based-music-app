package com.example.musicify.ui.fragments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Home() {
    Column(
        modifier = Modifier.fillMaxSize().background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Welcome to Musicify",
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Discover and enjoy unlimited music",
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Preview
@Composable
fun HomePreview() {
    Home()
}
