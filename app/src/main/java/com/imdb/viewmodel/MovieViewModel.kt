package com.imdb.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imdb.common.helper.LoadingViewState
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
class MovieViewModel @Inject constructor(private val useCase: MovieUseCase) : ViewModel() {

    var stateErrorMessage by mutableStateOf("")
        private set

    private val _movieState = MutableStateFlow(LoadingViewState<List<MovieState>>(emptyList()))
    val movieState = _movieState.asStateFlow()

    init {
        getTopRated()
    }

    fun getTopRated() {
        viewModelScope.launch {
            val newState = useCase.getTopRated()
                .fold({
                    stateErrorMessage = it.toString()
                    _movieState.value.asFailure()
                }, { model ->
                    _movieState.value.asSuccess(model.map { it.toMovieState() })
                })
            _movieState.update { newState }
        }
    }
}
