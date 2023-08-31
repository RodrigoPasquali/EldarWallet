package com.example.eldarwallet.domain.repository

import com.example.eldarwallet.domain.model.Card
import kotlinx.coroutines.flow.Flow

interface CardRepository {
    suspend fun saveCard(card: Card)
    suspend fun getCards(number: Long) : Flow<List<Card>>
}