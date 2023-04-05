package com.imdb.data.source.mapper

import com.imdb.core.helper.Either
import com.imdb.data.response.LatestResponse
import com.imdb.data.response.PopularResponse
import com.imdb.data.response.TopRatedResponse

fun TopRatedResponse.toEiterRight() = Either.Right(this)
fun PopularResponse.toEiterRight() = Either.Right(this)
fun LatestResponse.toEiterRight() = Either.Right(this)