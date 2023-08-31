package com.example.eldarwallet.ui.card

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eldarwallet.domain.action.GetCards
import com.example.eldarwallet.domain.action.SaveCard
import com.example.eldarwallet.domain.model.Card
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CardViewModel(
    private val getCards: GetCards,
    private val saveCard: SaveCard
) : ViewModel() {
    private var _cards = MutableLiveData<List<Card>>()
    var cards: LiveData<List<Card>> = _cards
    private var _registrationStatus = MutableLiveData<Boolean>()
    var registrationStatus: LiveData<Boolean> = _registrationStatus

    fun getCardsFromAccount(countNumber: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            getCards(countNumber).collect { cards ->
                _cards.postValue(cards)
            }
        }
    }

    fun registerCard(card: Card) {
        viewModelScope.launch(Dispatchers.IO) {
            if (validateNameUser(card)) {
                onCorrectValidationNameUser(card)
            } else {
                onCorrectInvalidationNameUser()
            }
        }
    }

    private fun onCorrectInvalidationNameUser() {
        _registrationStatus.postValue(false)
    }

    private suspend fun onCorrectValidationNameUser(card: Card) {
        saveCard(card)
        _registrationStatus.postValue(true)
    }

    private fun validateNameUser(card: Card): Boolean {
        return with(card) {
            (ownerName == "Flor" && ownerLastname == "Perez")
        }
    }
}