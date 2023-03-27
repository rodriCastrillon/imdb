package com.imdb.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imdb.common.extensionFunctions.timeStampExample
import com.imdb.common.helper.LoadState
import com.imdb.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class SplashViewModel @Inject constructor(private val useCase: LoginUseCase) : ViewModel() {
    var stateErrorMessage by mutableStateOf("")
    private val _loginState = MutableStateFlow<LoadState<Boolean>>(LoadState.InFlight)
    val loginState = _loginState.asStateFlow()
    fun onClear() = onCleared()

    init {
        isLogged()
    }

    private fun isLogged() {
        viewModelScope.launch {
            useCase.isLogged()
                .fold({
                    stateErrorMessage = it.message
                    _loginState.update { LoadState.Failure }
                }, { result ->
                    _loginState.update { LoadState.Success(result) }
                })
        }
        onClear()
    }

    override fun onCleared() {
        super.onCleared()
        _loginState.update { LoadState.InFlight }
    }
}
