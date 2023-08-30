package com.example.eldarwallet.infraestructure.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.eldarwallet.domain.model.Card

@Dao
interface CardDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(card: Card)

    @Query("select * from card_table where accountNumber = :number")
    fun getCards(number: Long): List<Card>
}