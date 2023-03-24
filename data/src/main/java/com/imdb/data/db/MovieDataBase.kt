package com.imdb.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MovieEntity::class, RegisterEntity::class], version = 1)
abstract class MovieDataBase : RoomDatabase() {
    abstract fun movieLocal(): MovieQuery
    abstract fun registerLocal(): RegisterQuery
}
