package com.imdb.core.remoteConfig

interface RemoteConfigHandler {
    val sessionTime:Int
}

object RemoteConfig : RemoteConfigHandler {
    override var sessionTime = 60
}
