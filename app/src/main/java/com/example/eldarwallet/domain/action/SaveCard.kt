package com.example.eldarwallet.domain.action

import com.example.eldarwallet.domain.model.Card
import com.example.eldarwallet.domain.repository.CardRepository

class SaveCard(private val repository: CardRepository) {
    suspend operator fun invoke(
        card: Card
    ) = repository.saveCard(card)
}
