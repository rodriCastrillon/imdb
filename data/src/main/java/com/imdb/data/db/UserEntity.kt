package com.imdb.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val _unique: Int = 0,
    val id: String,
    val email: String,
    val password: String,
    val name: String,
    val lastname: String,
    val provider: String,
    val urlPhoto: String,
    val token: String,
    val isLogged: Boolean,
    val sessionTime: Date
)
