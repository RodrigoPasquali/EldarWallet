package com.example.eldarwallet.domain.action

import com.example.eldarwallet.domain.repository.QRCodeRepository

class GenerateQR(private val repository: QRCodeRepository) {
    suspend operator fun invoke(
        value: String
    ) = repository.generateQR(value)
}
