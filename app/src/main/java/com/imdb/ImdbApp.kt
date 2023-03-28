package com.imdb

import android.app.Application
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.imdb.core.remoteConfig.RemoteConfig
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ImdbApp: Application() {

    override fun onCreate() {
        super.onCreate()
        //Remote Config
        val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 60000
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(mapOf("session_time" to 6))
        remoteConfig.fetchAndActivate().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val sessionTime = remoteConfig.getString("session_time")
                RemoteConfig.sessionTime = sessionTime.toInt()
            }
        }
    }
}
