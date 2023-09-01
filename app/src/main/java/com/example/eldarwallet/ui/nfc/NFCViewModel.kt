package com.example.eldarwallet.ui.nfc

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eldarwallet.domain.action.GetCards
import com.example.eldarwallet.domain.model.Card
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NFCViewModel(
    private val getCards: GetCards,
): ViewModel() {
    private var _cards = MutableLiveData<List<Card>>()
    var cards: LiveData<List<Card>> = _cards

    fun getCardsFromAccount(countNumber: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            getCards(countNumber).collect { cards ->
                _cards.postValue(cards)
            }
        }
    }
}
