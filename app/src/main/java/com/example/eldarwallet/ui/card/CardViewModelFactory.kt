package com.example.eldarwallet.ui.card

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.eldarwallet.domain.action.GetCards
import com.example.eldarwallet.domain.action.SaveCard

class CardViewModelFactory(
    private val getCards: GetCards,
    private val saveCard: SaveCard
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CardViewModel(getCards, saveCard) as T
    }
}