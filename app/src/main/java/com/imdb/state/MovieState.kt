package com.imdb.state

data class MovieState(
    val id: Int,
    val title: String,
    val overview: String,
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val original_language: String,
    val original_title: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)
