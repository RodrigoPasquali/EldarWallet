package com.example.eldarwallet.domain.repository

interface UserRepository {
    suspend fun login(email: String, password: String): Int
}
