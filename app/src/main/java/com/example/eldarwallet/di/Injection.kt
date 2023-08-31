package com.example.eldarwallet.di

import android.content.Context
import com.example.eldarwallet.domain.action.GetBalance
import com.example.eldarwallet.domain.action.GetCards
import com.example.eldarwallet.domain.action.Login
import com.example.eldarwallet.domain.action.SaveCard
import com.example.eldarwallet.domain.repository.AccountRepository
import com.example.eldarwallet.domain.repository.CardRepository
import com.example.eldarwallet.domain.repository.UserRepository
import com.example.eldarwallet.infraestructure.database.AppDatabase
import com.example.eldarwallet.infraestructure.repository.AccountRepositoryRoom
import com.example.eldarwallet.infraestructure.repository.CardRepositoryRoom
import com.example.eldarwallet.infraestructure.repository.UserRepositoryRoom

object Injection {
    fun provideGetBalance(context: Context) = GetBalance(provideAccountRepositoryRoom(context))

    fun provideGetCards(context: Context) = GetCards(provideCardRepositoryRoom(context))

    fun provideSaveCard(context: Context) = SaveCard(provideCardRepositoryRoom(context))

    fun provideLoginUser(context: Context) = Login(provideUserRepository(context))

    private fun provideAccountRepositoryRoom(context: Context): AccountRepository {
        return AccountRepositoryRoom(provideAppDataBase(context))
    }

    private fun provideCardRepositoryRoom(context: Context): CardRepository {
        return CardRepositoryRoom(provideAppDataBase(context))
    }

    private fun provideUserRepository(context: Context): UserRepository {
        return UserRepositoryRoom(provideAppDataBase(context))
    }

    private fun provideAppDataBase(context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }
}