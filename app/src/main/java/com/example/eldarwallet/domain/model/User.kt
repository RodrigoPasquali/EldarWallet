package com.example.eldarwallet.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey val email: String,
    val password: String,
    val accountNumber: Long
)
