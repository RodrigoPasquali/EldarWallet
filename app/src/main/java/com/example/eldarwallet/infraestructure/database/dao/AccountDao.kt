package com.example.eldarwallet.infraestructure.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.eldarwallet.domain.model.Account
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(account: Account)

    @Query("select balance from account_table where number = :number")
    fun getBalanceFromAccount(number: Long): Flow<Long>

    @Query("UPDATE account_table SET balance =:amount where number = :accountNumber")
    fun insertAmount(accountNumber: Long, amount: Long)
}