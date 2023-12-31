package com.example.eldarwallet.infraestructure.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.eldarwallet.domain.model.Account
import com.example.eldarwallet.domain.model.Card
import com.example.eldarwallet.domain.model.User
import com.example.eldarwallet.infraestructure.database.dao.AccountDao
import com.example.eldarwallet.infraestructure.database.dao.CardDao
import com.example.eldarwallet.infraestructure.database.dao.UserDao
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import java.util.concurrent.Executors

@Database(
    entities = [Account::class, Card::class, User::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getAccountDao(): AccountDao
    abstract fun getCardDao(): CardDao
    abstract fun getUserDao(): UserDao

    companion object {
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {

            if (instance == null) {
                val factory = SupportFactory(SQLiteDatabase.getBytes("R@s5P4ras3VeryL0n9".toCharArray()))

                instance = Room.databaseBuilder(context, AppDatabase::class.java, "EldarWallet")
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)

                            Executors.newSingleThreadExecutor().execute() {
                                instance?.getUserDao()?.create(User("flor@mail.com", "flor123", 1000,"Flor", "Perez"))
                                instance?.getUserDao()?.create(User("juan@mail.com", "juan123", 1001, "Juan", "Gonzalez"))
                                instance?.getUserDao()?.create(User("a", "a", 9999, "a", "a"))

                                instance?.getAccountDao()?.insert(Account(1000, 123456))
                                instance?.getAccountDao()?.insert(Account(1001, 987654))
                                instance?.getAccountDao()?.insert(Account(9999, 999999999))

                                instance?.getCardDao()?.insert(Card(4100000000001234, 1001, "Santander", 123, "01/23", "Juan", "Gonzalez"))
                                instance?.getCardDao()?.insert(Card(4200000000004321, 1000, "Galicia", 234, "02/23", "Flor", "Perez"))
                                instance?.getCardDao()?.insert(Card(5100000000006789, 1000, "BBVA", 345, "03/23", "Flor", "Perez"))
                                instance?.getCardDao()?.insert(Card(5200000000009876, 1001, "Nacion", 456, "04/23", "Juan", "Gonzalez"))
                                instance?.getCardDao()?.insert(Card(3100000000001478, 1001, "Provincia", 567, "05/23", "Juan", "Gonzalez"))
                                instance?.getCardDao()?.insert(Card(9999999999993698, 9999, "XXXXXX", 999, "99/99", "a", "a"))
                            }
                        }
                    })
                    .openHelperFactory(factory)
                    .fallbackToDestructiveMigration()
                    .build()
            }

            return instance as AppDatabase
        }
    }
}