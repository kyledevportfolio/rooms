
package com.example.android.persistence.codelab.step3

import android.app.Application

import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.example.android.persistence.codelab.db.AppDatabase
import com.example.android.persistence.codelab.db.Book
import com.example.android.persistence.codelab.db.utils.DatabaseInitializer


class BooksBorrowedByUserViewModel(application: Application) : AndroidViewModel(application) {

    val books: LiveData<List<Book>>?

    private var mDb: AppDatabase? = null

    init {
        createDb()

        // TODO: Assign books to the 'findBooksBorrowedByName' query.
        books = null
    }

    fun createDb() {
        mDb = AppDatabase.getInMemoryDatabase(this.getApplication())

        // Populate it with initial data
        DatabaseInitializer.populateAsync(mDb!!)
    }
}
