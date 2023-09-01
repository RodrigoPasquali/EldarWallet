package com.example.eldarwallet.domain.action

import com.example.eldarwallet.domain.repository.AccountRepository

class MakePayment(private val repository: AccountRepository) {
    suspend operator fun invoke(
        accountNumber: Long,
        paymentAmount: Long
    ) {
        val accountAmount = repository.getBalanceFromAccount(accountNumber)
        val newBalanceAmount = accountAmount - paymentAmount
        if (newBalanceAmount > 0) {
            repository.saveBalance(accountNumber, newBalanceAmount)
        } else {

        }
    }
}
