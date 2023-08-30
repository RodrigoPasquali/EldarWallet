package com.example.eldarwallet.domain.repository

import com.example.eldarwallet.domain.model.Card

interface CardRepository {
    suspend fun getCards(number: Long) : List<Card>
}