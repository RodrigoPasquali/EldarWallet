package com.example.eldarwallet.domain.model

data class UserSession(
    var accountNumber: Long,
    var ownerName: String,
    var ownerLastname: String
)