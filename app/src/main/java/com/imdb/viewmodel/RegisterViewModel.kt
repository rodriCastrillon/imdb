package com.imdb.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imdb.common.helper.LoadState
import com.imdb.domain.usecase.RegisterUseCase
import com.imdb.mapper.LoginProvider
import com.imdb.mapper.toRegisterModel
import com.imdb.state.RegisterState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class RegisterViewModel @Inject constructor(private val useCase: RegisterUseCase) : ViewModel() {

    var validatedPassword by mutableStateOf(true)

    private val _registerState = MutableStateFlow(RegisterState())
    val registerState = _registerState.asStateFlow()

    private val _resultState = MutableStateFlow<LoadState<Boolean>>(LoadState.InFlight)
    val resultState = _resultState.asStateFlow()

    fun register(password: String) {
        validatedPassword = validatePassword(password)
        if (validatedPassword) {
            _resultState.update { LoadState.Loading }
            viewModelScope.launch {
                val state = registerState.value.copy(
                    provider = LoginProvider.Manual.name
                ).toRegisterModel()

                useCase.register(state)
                    .fold({
                        _resultState.update { LoadState.Failure }
                    }, { result ->
                        _resultState.update { LoadState.Success(result) }
                    })
            }
        }
    }

    private fun validatePassword(password: String): Boolean =

        when {
            password.isEmpty() -> false
            else -> {
                val hasUppercase = Regex("[A-Z]").containsMatchIn(password)
                val hasDigits = Regex("\\d").containsMatchIn(password)
                val hasLowercase = Regex("[a-z]").containsMatchIn(password)
                val hasSpecialCharacters =
                    Regex("[!@#\$%^&*(),.?\":{}|<>]").containsMatchIn(password)
                val hasMinLength = password.length > 8

                hasDigits && hasUppercase && hasLowercase && hasSpecialCharacters && hasMinLength
            }
        }
}
