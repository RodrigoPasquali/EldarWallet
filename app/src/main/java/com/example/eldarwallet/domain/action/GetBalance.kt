package com.example.eldarwallet.domain.action

import com.example.eldarwallet.domain.repository.AccountRepository

class GetBalance(private val repository: AccountRepository) {
    suspend operator fun invoke(
        accountNumber: Long
    ) = repository.getBalanceFromAccount(accountNumber)
}