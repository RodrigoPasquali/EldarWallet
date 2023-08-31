package com.example.eldarwallet.infraestructure.repository

import com.example.eldarwallet.domain.model.Card
import com.example.eldarwallet.domain.repository.CardRepository
import com.example.eldarwallet.infraestructure.database.AppDatabase
import kotlinx.coroutines.flow.Flow

class CardRepositoryRoom(database: AppDatabase): CardRepository {
    private val cardDao = database.getCardDao()

    override suspend fun saveCard(card: Card) {
        cardDao.insert(card)
    }

    override suspend fun getCards(number: Long): Flow<List<Card>> {
        return cardDao.getCards(number)
    }
}