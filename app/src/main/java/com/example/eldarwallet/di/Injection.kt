package com.example.eldarwallet.di

import android.content.Context
import com.example.eldarwallet.domain.action.GenerateQR
import com.example.eldarwallet.domain.action.GetBalance
import com.example.eldarwallet.domain.action.GetCards
import com.example.eldarwallet.domain.action.GetUser
import com.example.eldarwallet.domain.action.Login
import com.example.eldarwallet.domain.action.MakePayment
import com.example.eldarwallet.domain.action.SaveCard
import com.example.eldarwallet.domain.repository.AccountRepository
import com.example.eldarwallet.domain.repository.CardRepository
import com.example.eldarwallet.domain.repository.QRCodeRepository
import com.example.eldarwallet.domain.repository.UserRepository
import com.example.eldarwallet.infraestructure.database.AppDatabase
import com.example.eldarwallet.infraestructure.remote.RetrofitClient
import com.example.eldarwallet.infraestructure.remote.service.QrCodeService
import com.example.eldarwallet.infraestructure.repository.AccountRepositoryRoom
import com.example.eldarwallet.infraestructure.repository.CardRepositoryRoom
import com.example.eldarwallet.infraestructure.repository.QRCodeRepositoryRemote
import com.example.eldarwallet.infraestructure.repository.UserRepositoryRoom

object Injection {
    fun provideGetBalance(context: Context) = GetBalance(provideAccountRepositoryRoom(context))

    fun provideGetCards(context: Context) = GetCards(provideCardRepositoryRoom(context))

    fun provideSaveCard(context: Context) = SaveCard(provideCardRepositoryRoom(context))

    fun provideLoginUser(context: Context) = Login(provideUserRepository(context))

    fun provideGetUser(context: Context) = GetUser(provideUserRepository(context))

    fun provideGenerateQR() = GenerateQR(provideQRCodeRepository())

    fun provideMakePayment(context: Context) = MakePayment(provideAccountRepositoryRoom(context))

    private fun provideAccountRepositoryRoom(context: Context): AccountRepository {
        return AccountRepositoryRoom(provideAppDataBase(context))
    }

    private fun provideCardRepositoryRoom(context: Context): CardRepository {
        return CardRepositoryRoom(provideAppDataBase(context))
    }

    private fun provideUserRepository(context: Context): UserRepository {
        return UserRepositoryRoom(provideAppDataBase(context))
    }

    private fun provideQRCodeRepository(): QRCodeRepository {
        return QRCodeRepositoryRemote(provideQrCodeApiService())
    }

    private fun provideAppDataBase(context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    private fun provideQrCodeApiService(): QrCodeService {
        return RetrofitClient.qrCodeService
    }
}