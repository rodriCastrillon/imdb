package com.imdb.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface MovieDAO {
    @Transaction
    suspend fun transaction(type:String, entity: List<MovieEntity>) {
        delete(type)
        insert(entity)
    }

    @Query("SELECT * FROM movie WHERE type = :type")
    fun select(type:String): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: List<MovieEntity>)

    @Query("DELETE FROM movie WHERE type = :type")
    fun delete(type: String)
}
