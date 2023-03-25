package com.imdb.viewmodel

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.imdb.R
import com.imdb.mapper.LoginProvider
import com.imdb.state.RegisterState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    fun handleSignInResult(task: Task<GoogleSignInAccount>) {
        try {
            val account = task.getResult(ApiException::class.java)

            account.idToken?.let { token ->
                val auth = FirebaseAuth.getInstance()
                val credential = GoogleAuthProvider.getCredential(token, null)
                auth.signInWithCredential(credential)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            signIgGoogle(
                                registerState = RegisterState(
                                    id = checkNotNull(account.id),
                                    email = checkNotNull(account.email),
                                    name = checkNotNull(account.givenName),
                                    lastname = checkNotNull(account.familyName),
                                    urlPhoto = checkNotNull(account.photoUrl.toString()),
                                    token = checkNotNull(account.idToken),
                                    provider = LoginProvider.Google.name
                                )
                            )
                        } else {
                            print("error")
                        }
                    }
            }

        } catch (e: ApiException) {
            print("")
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
        print(registerState)
    }
}
