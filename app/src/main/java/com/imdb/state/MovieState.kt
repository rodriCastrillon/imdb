package com.imdb.state

data class MovieState(
    val id: String = "",
    val title: String = "",
    val overview: String = "",
    val adult: Boolean = false,
    val backdrop_path: String = "",
    val genre_ids: List<Int> = listOf(),
    val original_language: String = "",
    val original_title: String = "",
    val popularity: Double = 0.0,
    val poster_path: String = "",
    val release_date: String = "",
    val video: Boolean = false,
    val vote_average: Double = 0.0,
    val vote_count: Int = 0
)
