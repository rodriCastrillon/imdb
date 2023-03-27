package com.imdb.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [MovieEntity::class, UserEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class MovieDataBase : RoomDatabase() {
    abstract fun movieLocal(): MovieQuery
    abstract fun registerLocal(): RegisterQuery
    abstract fun loginLocal(): LoginQuery
}
