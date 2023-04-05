package com.imdb.domain.model

import com.imdb.data.db.MovieEntity
import com.imdb.data.response.LatestResponse
import com.imdb.data.response.PopularDetailResponse
import com.imdb.data.response.PopularResponse
import com.imdb.data.response.TopRatedDetailResponse
import com.imdb.data.response.TopRatedResponse

fun getMovieModel(): List<MovieModel> =
    listOf(
        MovieModel(
            id = "238",
            adult = false,
            backdrop_path = "/tmU7GeKVybMWFButWEGl2M4GeiP.jpg",
            poster_path = "/3bhkrj58Vtu7enYsRolD1fZdja1.jpg",
            original_language = "en",
            original_title = "The Godfather",
            overview = "Spanning the years 1945 to 1955, a chronicle of the fictional Italian-American Corleone crime family. When organized crime family patriarch, Vito Corleone barely survives an attempt on his life, his youngest son, Michael steps in to take care of the would-be killers, launching a campaign of bloody revenge.",
            popularity = 120.733,
            release_date = "1972-03-14",
            title = "The Godfather",
            video = false,
            vote_average = 8.7,
            vote_count = 17642,
            genre_ids = listOf(18, 80)
        ),

        MovieModel(
            id = "278",
            adult = false,
            backdrop_path = "/wPU78OPN4BYEgWYdXyg0phMee64.jpg",
            poster_path = "/hBcY0fE9pfXzvVaY4GKarweriG2.jpg",
            original_language = "en",
            original_title = "The Shawshank Redemption",
            overview = "Framed in the 1940s for the double murder of his wife and her lover, upstanding banker Andy Dufresne begins a new life at the Shawshank prison, where he puts his accounting skills to work for an amoral warden. During his long stretch in prison, Dufresne comes to be admired by the other inmates -- including an older prisoner named Red -- for his integrity and unquenchable sense of hope.",
            popularity = 85.366,
            release_date = "1994-09-23",
            title = "The Shawshank Redemption",
            video = false,
            vote_average = 8.7,
            vote_count = 23481,
            genre_ids = listOf(18, 80)
        )
    )

fun topRatedResponseMock() = TopRatedResponse(
    page = 238,
    results = listOf(
        TopRatedDetailResponse(
            id = 238,
            adult = false,
            backdrop_path = "https://image.tmdb.org/t/p/w500/tmU7GeKVybMWFButWEGl2M4GeiP.jpg",
            poster_path = "/3bhkrj58Vtu7enYsRolD1fZdja1.jpg",
            original_language = "en",
            original_title = "The Godfather",
            overview = "Spanning the years 1945 to 1955, a chronicle of the fictional Italian-American Corleone crime family. When organized crime family patriarch, Vito Corleone barely survives an attempt on his life, his youngest son, Michael steps in to take care of the would-be killers, launching a campaign of bloody revenge.",
            popularity = 120.733,
            release_date = "1972-03-14",
            title = "The Godfather",
            video = false,
            vote_average = 8.7,
            vote_count = 17642,
            genre_ids = listOf(18, 80)
        ),

        TopRatedDetailResponse(
            id = 278,
            adult = false,
            backdrop_path = "https://image.tmdb.org/t/p/w500/tmU7GeKVybMWFButWEGl2M4GeiP.jpg",
            poster_path = "/hBcY0fE9pfXzvVaY4GKarweriG2.jpg",
            original_language = "en",
            original_title = "The Shawshank Redemption",
            overview = "Framed in the 1940s for the double murder of his wife and her lover, upstanding banker Andy Dufresne begins a new life at the Shawshank prison, where he puts his accounting skills to work for an amoral warden. During his long stretch in prison, Dufresne comes to be admired by the other inmates -- including an older prisoner named Red -- for his integrity and unquenchable sense of hope.",
            popularity = 85.366,
            release_date = "1994-09-23",
            title = "The Shawshank Redemption",
            video = false,
            vote_average = 8.7,
            vote_count = 23481,
            genre_ids = listOf(18, 80)
        )
    ),
    total_pages = 546,
    total_results = 10912
)

fun popularResponseMock() =
    PopularResponse(
        page = 1,
        results = listOf(
            PopularDetailResponse(
                id = 76600,
                adult = false,
                backdrop_path = "/ovM06PdF3M8wvKb06i4sjW3xoww.jpg",
                poster_path = "/t6HIqrRAclMCA60NsSmeqe9RmNV.jpg",
                genre_ids = listOf(878, 12, 28),
                original_language = "en",
                original_title = "Avatar: The Way of Water",
                overview = "Set more than a decade after the events of the first film, learn the story of the Sully family (Jake, Neytiri, and their kids), the trouble that follows them, the lengths they go to keep each other safe, the battles they fight to stay alive, and the tragedies they endure.",
                popularity = 8951.259,
                release_date = "2022-12-14",
                title = "Avatar: The Way of Water",
                video = false,
                vote_average = 7.8,
                vote_count = 6458
            ),

            PopularDetailResponse(
                id = 980078,
                adult = false,
                backdrop_path = "/wD2kUCX1Bb6oeIb2uz7kbdfLP6k.jpg",
                poster_path = "/s3u70iZ1mpY6W9rW1S6BxDMRNQt.jpg",
                genre_ids = listOf(27, 53),
                original_language = "en",
                original_title = "Winnie the Pooh: Blood and Honey",
                overview = "Christopher Robin is headed off to college and he has abandoned his old friends, Pooh and Piglet, which then leads to the duo embracing their inner monsters.",
                popularity = 3152.098,
                release_date = "2023-01-27",
                title = "Winnie the Pooh: Blood and Honey",
                video = false,
                vote_average = 5.9,
                vote_count = 327
            )
        ),
        total_pages = 37740,
        total_results = 754791
    )

fun latestResponseMock() =
    LatestResponse(
        id = 238,
        adult = false,
        backdrop_path = "https://image.tmdb.org/t/p/w500/tmU7GeKVybMWFButWEGl2M4GeiP.jpg",
        poster_path = "/3bhkrj58Vtu7enYsRolD1fZdja1.jpg",
        original_language = "en",
        original_title = "The Godfather",
        overview = "Spanning the years 1945 to 1955, a chronicle of the fictional Italian-American Corleone crime family. When organized crime family patriarch, Vito Corleone barely survives an attempt on his life, his youngest son, Michael steps in to take care of the would-be killers, launching a campaign of bloody revenge.",
        popularity = 120.733,
        release_date = "1972-03-14",
        title = "The Godfather",
        video = false,
        vote_average = 8.7,
        vote_count = 17642
    )

fun getMovieEntity() = listOf(
    MovieEntity(
        id = 238,
        type = "",
        adult = false,
        backdrop_path = "https://image.tmdb.org/t/p/w500/tmU7GeKVybMWFButWEGl2M4GeiP.jpg",
        poster_path = "/3bhkrj58Vtu7enYsRolD1fZdja1.jpg",
        original_language = "en",
        original_title = "The Godfather",
        overview = "Spanning the years 1945 to 1955, a chronicle of the fictional Italian-American Corleone crime family. When organized crime family patriarch, Vito Corleone barely survives an attempt on his life, his youngest son, Michael steps in to take care of the would-be killers, launching a campaign of bloody revenge.",
        popularity = 120.733,
        release_date = "1972-03-14",
        title = "The Godfather",
        video = false,
        vote_average = 8.7,
        vote_count = 17642
    ),

    MovieEntity(
        id = 278,
        type = "",
        adult = false,
        backdrop_path = "https://image.tmdb.org/t/p/w500/tmU7GeKVybMWFButWEGl2M4GeiP.jpg",
        poster_path = "/hBcY0fE9pfXzvVaY4GKarweriG2.jpg",
        original_language = "en",
        original_title = "The Shawshank Redemption",
        overview = "Framed in the 1940s for the double murder of his wife and her lover, upstanding banker Andy Dufresne begins a new life at the Shawshank prison, where he puts his accounting skills to work for an amoral warden. During his long stretch in prison, Dufresne comes to be admired by the other inmates -- including an older prisoner named Red -- for his integrity and unquenchable sense of hope.",
        popularity = 85.366,
        release_date = "1994-09-23",
        title = "The Shawshank Redemption",
        video = false,
        vote_average = 8.7,
        vote_count = 23481
    )
)
