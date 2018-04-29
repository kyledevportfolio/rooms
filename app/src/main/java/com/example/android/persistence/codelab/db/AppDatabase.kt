

package com.example.android.persistence.codelab.db

import android.content.Context

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase

@Database(entities = arrayOf(User::class, Book::class, Loan::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userModel(): UserDao

    abstract fun bookModel(): BookDao

    abstract fun loanModel(): LoanDao

    companion object {

        private var INSTANCE: AppDatabase? = null

        fun getInMemoryDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.inMemoryDatabaseBuilder<AppDatabase>(context.applicationContext, AppDatabase::class.java!!)
                        // To simplify the codelab, allow queries on the main thread.
                        // Don't do this on a real app! See PersistenceBasicSample for an example.
                        .allowMainThreadQueries()
                        .build()
            }
            return INSTANCE as AppDatabase
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}