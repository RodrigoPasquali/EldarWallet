package com.example.eldarwallet.infraestructure.remote

import com.example.eldarwallet.infraestructure.remote.service.QrCodeService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://neutrinoapi-qr-code.p.rapidapi.com/"
    private val gson = GsonBuilder().setLenient().create()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(providerOkHttpClient())
        .build()

    val qrCodeService: QrCodeService = retrofit.create(QrCodeService::class.java)

    private fun providerOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }
}