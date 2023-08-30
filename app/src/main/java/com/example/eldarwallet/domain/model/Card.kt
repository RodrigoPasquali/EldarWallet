package com.example.eldarwallet.domain.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "card_table", foreignKeys = [ForeignKey(entity = Account::class, parentColumns = ["number"], childColumns = ["accountNumber"])])
data class Card(
    @PrimaryKey val number: Long,
    val accountNumber: Long,
    val company: String,
    val securityCode: Int,
    val expirationDate: String,
)