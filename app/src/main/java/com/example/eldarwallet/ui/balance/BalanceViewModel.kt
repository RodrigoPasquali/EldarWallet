package com.example.eldarwallet.ui.balance

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eldarwallet.domain.action.GetBalance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BalanceViewModel(
    private val getBalance: GetBalance
) : ViewModel() {
    private var _balance = MutableLiveData<Long>()
    var balance: LiveData<Long> = _balance

    fun getBalance(countNumber: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _balance.postValue(getBalance(countNumber.toLong()))
        }
    }
}