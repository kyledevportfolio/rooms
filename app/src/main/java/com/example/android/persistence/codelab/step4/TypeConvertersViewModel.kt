

package com.example.android.persistence.codelab.step4

import android.app.Application

import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.example.android.persistence.codelab.db.AppDatabase
import com.example.android.persistence.codelab.db.Book
import com.example.android.persistence.codelab.db.utils.DatabaseInitializer


class TypeConvertersViewModel(application: Application) : AndroidViewModel(application) {

    var books: LiveData<List<Book>>? = null
        private set

    private var mDb: AppDatabase? = null

    init {
        createDb()
    }

    fun createDb() {
        mDb = AppDatabase.getInMemoryDatabase(this.getApplication())

        // Populate it with initial data
        DatabaseInitializer.populateAsync(mDb!!)

        // Receive changes
        subscribeToDbChanges()
    }

    private fun subscribeToDbChanges() {
        // Books is a LiveData object so updates are observed.
        // TODO: replace this with a query that finds books borrowed by Mike in the last 24h
        books = mDb!!.bookModel().findBooksBorrowedByName("Mike")
    }
}
