package com.example.eldarwallet.infraestructure.repository

import com.example.eldarwallet.domain.repository.QRCodeRepository
import com.example.eldarwallet.infraestructure.remote.service.QrCodeService
import okhttp3.ResponseBody
import retrofit2.Response

class QRCodeRepositoryRemote(
    private val service: QrCodeService,
): QRCodeRepository {
    override suspend fun generateQR(value: String): Response<ResponseBody> {
        return service.generateQR(value)
    }
}