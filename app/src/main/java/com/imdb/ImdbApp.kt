package com.imdb

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ImdbApp:Application(){
    override fun onCreate() {
        super.onCreate()
    }
}
