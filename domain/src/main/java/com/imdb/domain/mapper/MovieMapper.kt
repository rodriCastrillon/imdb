package com.imdb.domain.mapper

import com.imdb.core.Constants.BASE_URL_MAGE
import com.imdb.data.db.MovieEntity
import com.imdb.data.mapper.MovieType
import com.imdb.data.response.LatestResponse
import com.imdb.data.response.PopularDetailResponse
import com.imdb.data.response.TopRatedDetailResponse
import com.imdb.domain.model.MovieModel

fun TopRatedDetailResponse.toMovieModel() = MovieModel(
    id = id.toString(),
    adult = adult,
    genre_ids = genre_ids,
    original_language = original_language,
    original_title = original_title,
    overview = overview,
    popularity = popularity,
    backdrop_path = BASE_URL_MAGE.plus(backdrop_path),
    poster_path = BASE_URL_MAGE.plus(poster_path),
    release_date = release_date,
    title = title,
    video = video,
    vote_average = vote_average,
    vote_count = vote_count
)

fun TopRatedDetailResponse.toMovieEntity() = MovieEntity(
    id = id,
    type = MovieType.TopRated.name,
    adult = adult,
    original_language = original_language,
    original_title = original_title,
    overview = overview,
    popularity = popularity,
    backdrop_path = BASE_URL_MAGE.plus(backdrop_path),
    poster_path = BASE_URL_MAGE.plus(poster_path),
    release_date = release_date,
    title = title,
    video = video,
    vote_average = vote_average,
    vote_count = vote_count
)

fun MovieEntity.toMovieModel() = MovieModel(
    id = id.toString(),
    adult = adult,
    backdrop_path = backdrop_path,
    genre_ids = emptyList(),
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

fun PopularDetailResponse.toMovieModel() = MovieModel(
    id = id.toString(),
    adult = adult,
    genre_ids = genre_ids,
    original_language = original_language,
    original_title = original_title,
    overview = overview,
    popularity = popularity,
    backdrop_path = BASE_URL_MAGE.plus(backdrop_path),
    poster_path = BASE_URL_MAGE.plus(poster_path),
    release_date = release_date,
    title = title,
    video = video,
    vote_average = vote_average,
    vote_count = vote_count
)

fun PopularDetailResponse.toMovieEntity() = MovieEntity(
    id = id,
    type = MovieType.Popular.name,
    adult = adult,
    original_language = original_language,
    original_title = original_title,
    overview = overview,
    popularity = popularity,
    backdrop_path = BASE_URL_MAGE.plus(backdrop_path),
    poster_path = BASE_URL_MAGE.plus(poster_path),
    release_date = release_date,
    title = title,
    video = video,
    vote_average = vote_average,
    vote_count = vote_count
)

fun LatestResponse.toMovieModel() = MovieModel(
    id = id.toString(),
    adult = adult,
    genre_ids = emptyList(),
    original_language = original_language,
    original_title = original_title,
    overview = overview,
    popularity = popularity,
    backdrop_path = BASE_URL_MAGE.plus(backdrop_path),
    poster_path = BASE_URL_MAGE.plus(poster_path),
    release_date = release_date,
    title = title,
    video = video,
    vote_average = vote_average,
    vote_count = vote_count
)

fun LatestResponse.toMovieEntity() = MovieEntity(
    id = id,
    type = MovieType.Latest.name,
    adult = adult,
    original_language = original_language,
    original_title = original_title,
    overview = overview,
    popularity = popularity,
    backdrop_path = BASE_URL_MAGE.plus(backdrop_path),
    poster_path = BASE_URL_MAGE.plus(poster_path),
    release_date = release_date,
    title = title,
    video = video,
    vote_average = vote_average,
    vote_count = vote_count
)
