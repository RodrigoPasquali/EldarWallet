package com.example.eldarwallet.domain.repository

import okhttp3.ResponseBody
import retrofit2.Response

interface QRCodeRepository {
    suspend fun generateQR(value: String): Response<ResponseBody>
}
