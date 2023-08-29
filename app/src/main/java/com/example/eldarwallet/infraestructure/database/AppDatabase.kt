package com.example.eldarwallet.infraestructure.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.eldarwallet.domain.model.Account
import com.example.eldarwallet.infraestructure.database.dao.AccountDao
import java.util.concurrent.Executors

@Database(
    entities = [Account::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getAccountDao(): AccountDao

    companion object {
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(context, AppDatabase::class.java, "EldarWallet")
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)

                            Executors.newSingleThreadExecutor().execute() {
                                instance?.getAccountDao()?.insert(Account(1000, 123456))
                                instance?.getAccountDao()?.insert(Account(1001, 987654))
                            }
                        }
                    })
                    .fallbackToDestructiveMigration()
                    .build()
            }

            return instance as AppDatabase
        }
    }
}