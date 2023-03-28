package com.imdb.viewmodel

import android.app.Activity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.imdb.R
import com.imdb.core.extensionFunctions.toHashSha1
import com.imdb.core.helper.LoadState
import com.imdb.core.helper.LoginProvider
import com.imdb.domain.usecase.LoginUseCase
import com.imdb.domain.usecase.RegisterUseCase
import com.imdb.mapper.toRegisterModel
import com.imdb.mapper.toRegisterState
import com.imdb.mapper.toUserState
import com.imdb.state.RegisterState
import com.imdb.state.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase
) : ViewModel() {
    var stateErrorMessage by mutableStateOf("")
    private val _loginState = MutableStateFlow<LoadState<RegisterState>>(LoadState.InFlight)
    val loginState = _loginState.asStateFlow()
    var userSate by mutableStateOf(UserState())

    var isUserNameFilled by mutableStateOf(true)
    var isPasswordFilled by mutableStateOf(true)
    fun onClear() = onCleared()
    fun handleSignInResult(task: Task<GoogleSignInAccount>) {
        try {
            val account = task.getResult(ApiException::class.java)

            account.idToken?.let { token ->
                val auth = FirebaseAuth.getInstance()
                val credential = GoogleAuthProvider.getCredential(token, null)
                auth.signInWithCredential(credential)
                    .addOnCompleteListener { task ->
                        when {
                            task.isSuccessful -> {
                                signIgGoogle(
                                    registerState = RegisterState(
                                        id = checkNotNull(account.id),
                                        email = checkNotNull(account.email),
                                        name = checkNotNull(account.givenName),
                                        lastname = checkNotNull(account.familyName),
                                        urlPhoto = checkNotNull(account.photoUrl.toString()),
                                        token = checkNotNull(account.idToken),
                                        provider = LoginProvider.Google.name,
                                        isLogged = true
                                    )
                                )
                            }
                            else -> stateErrorMessage = checkNotNull(task.exception?.message)
                        }
                    }
            }
        } catch (apiException: ApiException) {
            stateErrorMessage = checkNotNull(apiException.message)
        }
    }

    fun loginAuthGoogle(activity: Activity): GoogleSignInClient {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestIdToken(activity.getString(R.string.default_web_client_id))
            .requestId()
            .requestProfile()
            .build()
        return GoogleSignIn.getClient(activity, gso)
    }

    private fun signIgGoogle(registerState: RegisterState) {
        viewModelScope.launch {
            registerUseCase.register(registerState.toRegisterModel())
                .fold({
                    stateErrorMessage = it.message
                    _loginState.update { LoadState.Failure }
                }, {
                    userSate = registerState.toUserState()
                    _loginState.update { LoadState.Success(registerState) }
                })
        }
    }

    fun signManual(email: String, password: String) {
        isUserNameFilled = email.isNotEmpty()
        isPasswordFilled = password.isNotEmpty()

        when {
            isUserNameFilled && isPasswordFilled -> {
                viewModelScope.launch {
                    loginUseCase.login(
                        email = email,
                        password = (email.plus(password).toHashSha1())
                    )
                        .fold({
                            stateErrorMessage = it.message
                            _loginState.update { LoadState.Failure }
                        }, { result ->
                            userSate = result.toUserState()
                            _loginState.update { LoadState.Success(result.toRegisterState()) }
                        })
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        _loginState.update { LoadState.InFlight }
    }
}
