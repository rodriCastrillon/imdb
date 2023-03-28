object App {
    const val minSdk = 24
    const val compileSdk = 33
    const val targetSdk = 33
    const val versionCode = 1

    const val versionName = "1.0.0"
    const val nameSpace = "com.imdb"
    const val jvmTarget = "1.8"
}

object Versions {
    const val hilt    = "2.44.2"
}

object Libraries {

    object Android {
        const val hilt                  = "com.google.dagger:hilt-android:2.44.2"
        const val kapt                  = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    }

    object Compose {

    }

    object Retrofit {

    }

    object Room {

    }

}
