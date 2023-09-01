package com.example.eldarwallet.ui.nfc

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eldarwallet.domain.action.GetCards
import com.example.eldarwallet.domain.action.MakePayment
import com.example.eldarwallet.domain.model.Card
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NFCViewModel(
    private val getCards: GetCards,
    private val makePayment: MakePayment
): ViewModel() {
    private var _cards = MutableLiveData<List<Card>>()
    var cards: LiveData<List<Card>> = _cards

    private var _paymentStatus = MutableLiveData<Boolean>()
    var paymentStatus: LiveData<Boolean> = _paymentStatus

    fun getCardsFromAccount(acountNumber: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            getCards(acountNumber).collect { cards ->
                _cards.postValue(cards)
            }
        }
    }

    fun makeNFCPayment(accountNumber: Long, amount: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            if (makePayment.invoke(accountNumber, amount)) {
                _paymentStatus.postValue(true)
            } else {
                _paymentStatus.postValue(false)
            }
        }
    }
}
