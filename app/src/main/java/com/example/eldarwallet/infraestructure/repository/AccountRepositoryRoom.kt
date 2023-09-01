package com.example.eldarwallet.infraestructure.repository

import com.example.eldarwallet.domain.model.Account
import com.example.eldarwallet.domain.repository.AccountRepository
import com.example.eldarwallet.infraestructure.database.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AccountRepositoryRoom(database: AppDatabase) : AccountRepository {
    private val accountDao = database.getAccountDao()

    override suspend fun saveAccount(account: Account) {
        withContext(Dispatchers.IO) {
            accountDao.insert(account)
        }
    }

    override suspend fun saveBalance(accountNumber: Long, amount: Long) {
        withContext(Dispatchers.IO) {
            accountDao.insertAmount(accountNumber, amount)
        }
    }

    override suspend fun getBalanceFromAccount(accountNumber: Long): Long {
        return withContext(Dispatchers.IO) {
            accountDao.getBalanceFromAccount(accountNumber)
        }
    }
}