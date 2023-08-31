package com.example.eldarwallet.domain.repository

import com.example.eldarwallet.domain.model.User

interface UserRepository {
    suspend fun login(email: String, password: String): Int
    suspend fun getUser(email: String): User
}
