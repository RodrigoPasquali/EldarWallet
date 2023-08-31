package com.example.eldarwallet.domain.action

import com.example.eldarwallet.domain.repository.UserRepository

class GetUser(private val repository: UserRepository) {
    suspend operator fun invoke(
        email: String
    ) = repository.getUser(email)
}