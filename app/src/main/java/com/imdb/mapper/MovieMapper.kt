package com.imdb.mapper

import com.imdb.domain.model.MovieModel
import com.imdb.state.MovieState

fun MovieModel.toMovieState() = MovieState(
    id = id,
    adult = adult,
    backdrop_path = backdrop_path,
    genre_ids = genre_ids,
    original_language = original_language,
    original_title = original_title,
    overview = overview,
    popularity = popularity,
    poster_path = poster_path,
    release_date = release_date,
    title = title,
    video = video,
    vote_average = vote_average,
    vote_count = vote_count
)
