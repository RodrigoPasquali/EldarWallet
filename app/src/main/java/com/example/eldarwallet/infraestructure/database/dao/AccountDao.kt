package com.example.eldarwallet.infraestructure.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.eldarwallet.domain.model.Account

@Dao
interface AccountDao {
    @Insert
    fun insert(account: Account)

    @Query("select balance from account_table where number = :number")
    fun getBalanceFromAccount(number: Long): Long
}