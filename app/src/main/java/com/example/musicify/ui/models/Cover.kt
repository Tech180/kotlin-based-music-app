package com.example.musicify.ui.models
import androidx.annotation.Keep

@Keep
data class Cover (
    val id: Int,
    val name: String,
    val posterPath: String,
    val backdropPath: String,
    val overview: String
)