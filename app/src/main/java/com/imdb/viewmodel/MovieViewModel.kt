package com.imdb.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imdb.core.helper.LoadingViewState
import com.imdb.domain.usecase.MovieUseCase
import com.imdb.mapper.toMovieState
import com.imdb.state.MovieState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val useCase: MovieUseCase
) : ViewModel() {

    var stateErrorMessage by mutableStateOf("")

    private val _movieState = MutableStateFlow(LoadingViewState<List<MovieState>>(emptyList()))
    val movieState = _movieState.asStateFlow()

    init {
        getTopRated()
    }

    private fun getTopRated() {
        viewModelScope.launch {
            val newState = useCase.getTopRated()
                .fold({
                    stateErrorMessage = it.message
                    _movieState.value.asFailure()
                }, { model ->
                    _movieState.value.asSuccess(model.map { it.toMovieState() })
                })
            _movieState.update { newState }
        }
    }

    fun searchQuery(query: String): List<MovieState> =
        when (query.isNotEmpty()) {
            true -> {
                val moviesFiltered = mutableListOf<MovieState>()

                _movieState.value.data.forEach { movie ->
                    if (movie.title.lowercase().contains(query.lowercase())) {
                        moviesFiltered.add(movie)
                    }
                }

                moviesFiltered
            }
            else -> _movieState.value.data
        }
}
