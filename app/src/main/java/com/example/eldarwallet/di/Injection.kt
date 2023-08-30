package com.example.eldarwallet.di

import android.content.Context
import com.example.eldarwallet.domain.action.GetBalance
import com.example.eldarwallet.domain.repository.AccountRepository
import com.example.eldarwallet.infraestructure.database.AppDatabase
import com.example.eldarwallet.infraestructure.repository.AccountRepositoryRoom

object Injection {
    fun provideGetBalance(context: Context) = GetBalance(provideAccountRepositoryRoom(context))

    private fun provideAccountRepositoryRoom(context: Context): AccountRepository {
        return AccountRepositoryRoom(provideAppDataBase(context))
    }

    private fun provideAppDataBase(context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }
}