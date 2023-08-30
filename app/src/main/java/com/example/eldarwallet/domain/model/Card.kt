package com.example.eldarwallet.domain.model

data class Card(
    val number: Long,
    val company: String,
    val securityCode: Int,
    val expirationDate: String,
    val type: String
)