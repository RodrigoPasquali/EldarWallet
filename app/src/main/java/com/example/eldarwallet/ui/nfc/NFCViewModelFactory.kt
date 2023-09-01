package com.example.eldarwallet.ui.nfc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.eldarwallet.domain.action.GetCards
import com.example.eldarwallet.domain.action.MakePayment

class NFCViewModelFactory(
    private val getCards: GetCards,
    private val makePayment: MakePayment
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NFCViewModel(getCards, makePayment) as T
    }
}