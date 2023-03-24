package com.imdb.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface RegisterQuery {
    @Transaction
    suspend fun transaction(entity: RegisterEntity) {
        delete()
        insert(entity)
    }

    @Query("SELECT * FROM register")
    fun select(): RegisterEntity

    @Query("SELECT COUNT(email) FROM register WHERE email = :email")
    suspend fun selectByEmail(email:String): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: RegisterEntity)

    @Query("DELETE FROM register")
    fun delete()
}
