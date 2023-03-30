package com.imdb.data.db

import androidx.room.Dao
import androidx.room.Query
import java.util.Calendar
import java.util.Date

@Dao
interface LoginDAO {

    @Query("SELECT * FROM user")
    fun select(): UserEntity?

    @Query("SELECT * FROM user WHERE email = :email AND password = :password")
    fun selectByEmailAndPassword(email: String, password:String): UserEntity?

    @Query("UPDATE user SET sessionTime = :sessionTime")
    fun updateSession(sessionTime: Date = Calendar.getInstance().time)
}
