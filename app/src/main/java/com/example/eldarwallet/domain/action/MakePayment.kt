package com.example.eldarwallet.domain.action

import com.example.eldarwallet.domain.repository.AccountRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

class MakePayment(private val repository: AccountRepository) {
    suspend operator fun invoke(
        accountNumber: Long,
        paymentAmount: Long
    ): Boolean {
        return withContext(Dispatchers.IO) {
            val newBalanceAmount: Long
            val balanceAmount = repository.getBalanceFromAccount(accountNumber).first()

            newBalanceAmount = balanceAmount - paymentAmount

            if (newBalanceAmount > 0L) {
                repository.saveBalance(accountNumber, newBalanceAmount)
                true
            } else {
                false
            }
        }
    }
}
