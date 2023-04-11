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
        private set

    private val _topRatedState = MutableStateFlow(LoadingViewState<List<MovieState>>(emptyList()))
    val topRatedState = _topRatedState.asStateFlow()

    private val _popularState = MutableStateFlow(LoadingViewState<List<MovieState>>(emptyList()))
    val popularStateState = _popularState.asStateFlow()

    private val _latestState = MutableStateFlow(LoadingViewState(MovieState()))
    val latestState = _latestState.asStateFlow()

    init {
        getTopRated()
    }

    private fun getTopRated() {
        viewModelScope.launch {
            val newState = useCase.getTopRated()
                .fold({
                    stateErrorMessage = it.message
                    _topRatedState.value.asFailure()
                }, { model ->
                    _topRatedState.value.asSuccess(model.map { it.toMovieState() })
                })
            _topRatedState.update { newState }
        }

        getPopular()
    }

    private fun getPopular() {
        viewModelScope.launch {
            val newState = useCase.getPopular()
                .fold({
                    stateErrorMessage = it.message
                    _popularState.value.asFailure()
                }, { model ->
                    _popularState.value.asSuccess(model.map { it.toMovieState() })
                })
            _popularState.update { newState }
            //_latestState.update{_latestState.value.asSuccess(newState.data.single())}
        }


        //getLatest()
    }

    private fun getLatest() {
        viewModelScope.launch {
            val newState = useCase.getLatest()
                .fold({
                    stateErrorMessage = it.message
                    _latestState.value.asFailure()
                }, { model ->
                    _latestState.value.asSuccess(model.toMovieState())
                })
            _latestState.update { newState }
        }
    }

    fun searchQuery(query: String): List<MovieState> =
        when (query.isNotEmpty()) {
            true -> {
                val moviesFiltered = mutableListOf<MovieState>()

                _topRatedState.value.data.forEach { movie ->
                    if (movie.title.lowercase().contains(query.lowercase())) {
                        moviesFiltered.add(movie)
                    }
                }

                moviesFiltered
            }
            else -> _topRatedState.value.data
        }
}
