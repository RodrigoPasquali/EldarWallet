package com.example.eldarwallet.infraestructure.remote.service

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface QrCodeService {
    @Headers(
        CONTENT_TYPE,
        X_RAPIDAPI_KEY,
        X_RAPIDAPI_HOST
    )
    @POST("qr-code")
    suspend fun generateQR(@Query("content") value: String): Response<ResponseBody>

    private companion object {
        const val CONTENT_TYPE = "content-type: application/x-www-form-urlencoded"
        const val X_RAPIDAPI_KEY = "X-RapidAPI-Key: 20a612ba96msheb1d56c6f858e46p1c35f8jsn4b5baca8e3f8"
        const val X_RAPIDAPI_HOST = "X-RapidAPI-Host: neutrinoapi-qr-code.p.rapidapi.com"
    }
}