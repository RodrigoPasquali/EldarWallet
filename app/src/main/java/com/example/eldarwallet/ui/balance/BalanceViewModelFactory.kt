package com.example.eldarwallet.ui.balance

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.eldarwallet.domain.action.GetBalance

class BalanceViewModelFactory(
    private val getBalance: GetBalance
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BalanceViewModel(getBalance) as T
    }
}