package com.imdb.data.source.mapper

import com.imdb.common.helper.Either
import com.imdb.data.response.MovieResponse

fun MovieResponse.toEiterRight() = Either.Right(this)