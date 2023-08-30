package com.example.eldarwallet.infraestructure.repository

import com.example.eldarwallet.domain.model.Card
import com.example.eldarwallet.domain.repository.CardRepository
import com.example.eldarwallet.infraestructure.database.AppDatabase

class CardRepositoryRoom(database: AppDatabase): CardRepository {
    private val cardDao = database.getCardDao()

    override suspend fun getCards(number: Long): List<Card> {
        return cardDao.getCards(number)
    }
}