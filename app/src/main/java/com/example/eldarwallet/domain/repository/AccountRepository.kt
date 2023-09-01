package com.example.eldarwallet.domain.repository

import com.example.eldarwallet.domain.model.Account

interface AccountRepository {
    suspend fun saveAccount(account: Account)
    suspend fun saveBalance(accountNumber: Long, amount: Long)
    suspend fun getBalanceFromAccount(accountNumber: Long) : Long
}