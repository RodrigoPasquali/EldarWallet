package com.example.eldarwallet.domain.action

import com.example.eldarwallet.domain.repository.CardRepository

class GetCards(private val repository: CardRepository) {
    suspend operator fun invoke(
        accountNumber: Long
    ) = repository.getCards(accountNumber)
}