package com.imdb.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "register")
data class RegisterEntity(
    @PrimaryKey(autoGenerate = true)
    val _unique: Int = 0,
    val id: Int,
    val email: String,
    val password: String,
    val name: String,
    val lastname: String,
    val provider: String
)
