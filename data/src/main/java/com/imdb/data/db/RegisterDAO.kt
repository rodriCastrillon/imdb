package com.imdb.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface RegisterDAO {
    @Transaction
    suspend fun transaction(entity: UserEntity) {
        delete()
        insert(entity)
    }

    @Query("SELECT * FROM user")
    fun select(): UserEntity

    @Query("SELECT COUNT(email) FROM user WHERE email = :email")
    suspend fun selectByEmail(email:String): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: UserEntity)

    @Query("DELETE FROM user")
    fun delete()
}
