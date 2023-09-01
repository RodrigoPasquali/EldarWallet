package com.example.eldarwallet.ui.nfc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.eldarwallet.domain.action.GetCards

class NFCViewModelFactory(
    private val getCards: GetCards,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NFCViewModel(getCards) as T
    }
}