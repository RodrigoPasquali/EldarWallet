package com.example.eldarwallet.ui.card

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.eldarwallet.domain.action.GetCards

class CardViewModelFactory(
    private val getCards: GetCards
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CardViewModel(getCards) as T
    }
}