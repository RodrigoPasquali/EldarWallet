package com.example.eldarwallet.di

import android.content.Context
import com.example.eldarwallet.domain.action.GetBalance
import com.example.eldarwallet.domain.action.GetCards
import com.example.eldarwallet.domain.repository.AccountRepository
import com.example.eldarwallet.domain.repository.CardRepository
import com.example.eldarwallet.infraestructure.database.AppDatabase
import com.example.eldarwallet.infraestructure.repository.AccountRepositoryRoom
import com.example.eldarwallet.infraestructure.repository.CardRepositoryRoom

object Injection {
    fun provideGetBalance(context: Context) = GetBalance(provideAccountRepositoryRoom(context))

    fun provideGetCards(context: Context) = GetCards(provideCardRepositoryRoom(context))

    private fun provideAccountRepositoryRoom(context: Context): AccountRepository {
        return AccountRepositoryRoom(provideAppDataBase(context))
    }

    private fun provideCardRepositoryRoom(context: Context): CardRepository {
        return CardRepositoryRoom(provideAppDataBase(context))
    }

    private fun provideAppDataBase(context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }
}