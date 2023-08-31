package com.example.eldarwallet.infraestructure.repository

import com.example.eldarwallet.domain.model.User
import com.example.eldarwallet.domain.repository.UserRepository
import com.example.eldarwallet.infraestructure.database.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepositoryRoom(database: AppDatabase) : UserRepository {
    private val userDao = database.getUserDao()

    override suspend fun login(email: String, password: String): Int  {
        return withContext(Dispatchers.IO) {
            userDao.authenticate(email, password)
        }
    }

    override suspend fun getUser(email: String): User {
        return withContext(Dispatchers.IO) {
            userDao.getUser(email)
        }
    }
}