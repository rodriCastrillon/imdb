package com.imdb.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface MovieLocal {
    @Transaction
    suspend fun transaction(entity: List<MovieEntity>) {
        delete()
        insert(entity)
    }

    @Query("SELECT * FROM movie")
    fun select(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: List<MovieEntity>)

    @Query("DELETE FROM movie")
    fun delete()
}
