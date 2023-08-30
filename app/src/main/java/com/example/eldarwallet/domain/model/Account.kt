package com.example.eldarwallet.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "account_table")
data class Account(
    @PrimaryKey val number: Long,
    var balance: Long = 0,
)