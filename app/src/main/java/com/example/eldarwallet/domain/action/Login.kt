package com.example.eldarwallet.domain.action

import com.example.eldarwallet.domain.repository.UserRepository

class Login(private val repository: UserRepository) {
    suspend operator fun invoke(
        email: String,
        password: String
    ) = repository.login(email, password)
}