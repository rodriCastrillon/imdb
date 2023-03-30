package com.imdb.ui.dummy

import com.imdb.domain.model.MovieModel
import com.imdb.domain.model.RegisterModel

fun getTopRated(): List<MovieModel> =
    listOf(
        MovieModel(
            id = "238",
            adult = false,
            backdrop_path = "/tmU7GeKVybMWFButWEGl2M4GeiP.jpg",
            poster_path = "/3bhkrj58Vtu7enYsRolD1fZdja1.jpg",
            genre_ids = listOf(18, 80),
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

        MovieModel(
            id = "278",
            adult = false,
            backdrop_path = "/wPU78OPN4BYEgWYdXyg0phMee64.jpg",
            poster_path = "/hBcY0fE9pfXzvVaY4GKarweriG2.jpg",
            genre_ids = listOf(18, 80),
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

fun getUser() = RegisterModel(
    id = "82343a02-d9a8-44be-a481-e8c6007830e5",
    email = "test@gmail.com",
    password = "1234",
    name = "Rodrigo",
    lastname = "Castrillon",
    provider = "manual",
    urlPhoto = "https://image.tmdb.org/t/p/w500/y5Z0WesTjvn59jP6yo459eUsbli.jpg",
    token = "3453463463434",
    isLogged = false
)
/*
fun getComingModel() = listOf(
    UpcomingModel(
        id = 123456,
        backdrop_path = Constants.IMAGE_URL.plus("/y5Z0WesTjvn59jP6yo459eUsbli.jpg"),
        original_language = "en",
        release_date = "2022-10-06",
        original_title = "Terrifier 2",
        overview = "After being resurrected by a sinister entity."
    )
)

fun getUpcomingEntityDummy() = listOf(
    UpcomingEntity(
        id = 123456,
        backdrop_path = "/y5Z0WesTjvn59jP6yo459eUsbli.jpg",
        original_language = "en",
        release_date = "2022-10-06",
        original_title = "Terrifier 2",
        overview = "After being resurrected by a sinister entity."
    )
)

fun topRatedResponseDummy() = TopRatedResponse(
    listOf(
        ResultTopRatedResponse(
            id = 667556,
            backdrop_path ="/y5Z0WesTjvn59jP6yo459eUsbli.jpg",
            release_date = "2022-10-06",
            original_language = "en",
            original_title = "Terrifier 2",
            overview = "After being resurrected by a sinister entity.",
            vote_average = 8.5
        )
    )
)


fun topRatedEntityDummy() =
    listOf(
        TopRatedEntity(
            id = 667556,
            backdrop_path = Constants.IMAGE_URL.plus("/y5Z0WesTjvn59jP6yo459eUsbli.jpg"),
            release_date = "2022-10-06",
            original_language = "en",
            original_title = "Terrifier 2",
            overview = "After being resurrected by a sinister entity.",
            vote_average = 8.5
        )
    )

fun getTopRatedModelDummy() = listOf(
    TopRatedModel(
        id = 667556,
        backdrop_path = Constants.IMAGE_URL.plus("/y5Z0WesTjvn59jP6yo459eUsbli.jpg"),
        release_date = "2022-10-06",
        original_language = "en",
        original_title = "Terrifier 2",
        overview = "After being resurrected by a sinister entity.",
        vote_average = 8.5
    )
)

fun detailResponseDummy() = DetailResponse(
    id = 454353,
    release_date = "2021-12-15",
    original_language = "en",
    vote_average = 8.029,
    genres = listOf(GenreResponse(id = 28, name = "Action")),
    title = "Spider-Man: No Way Home",
    overview = "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero.",
    backdrop_path = "/14QbnygCuTO0vl7CAFmPf1fgZfV.jpg"
)

fun detailModelDummy() = DetailModel(
    id = 454353,
    release_date = "2021-12-15",
    original_language = "en",
    vote_average = 8.029,
    genres = listOf(GenreModel(id = 28, name = "Action")),
    title = "Spider-Man: No Way Home",
    overview = "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero.",
    backdrop_path = Constants.IMAGE_URL.plus("/14QbnygCuTO0vl7CAFmPf1fgZfV.jpg")
)

fun getTrailerResponseDummy() = TrailerResponse(listOf(Result(key = "Uty1B1GuO7E")))

 */