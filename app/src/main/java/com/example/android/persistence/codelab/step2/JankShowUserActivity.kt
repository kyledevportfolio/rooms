

package com.example.android.persistence.codelab.step2

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView

import com.example.android.codelabs.persistence.R
import com.example.android.persistence.codelab.db.AppDatabase
import com.example.android.persistence.codelab.db.Book
import com.example.android.persistence.codelab.db.utils.DatabaseInitializer

class JankShowUserActivity : AppCompatActivity() {

    private var mDb: AppDatabase? = null

    private var mBooksTextView: TextView? = null

    private fun showListInUI(books: List<Book>,
                             booksTextView: TextView?) {
        val sb = StringBuilder()
        for (book in books) {
            sb.append(book.title)
            sb.append("\n")
        }
        booksTextView!!.text = sb.toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.db_activity)

        mBooksTextView = findViewById(R.id.books_tv)

        // Note: Db references should not be in an activity.
        mDb = AppDatabase.getInMemoryDatabase(applicationContext)

        populateDb()

        fetchData()
    }

    override fun onDestroy() {
        AppDatabase.destroyInstance()
        super.onDestroy()
    }

    private fun populateDb() {
        DatabaseInitializer.populateSync(mDb!!)
    }

    private fun fetchData() {
        // This activity is executing a query on the main thread, making the UI perform badly.
        val books = mDb!!.bookModel().findBooksBorrowedByNameSync("Mike")
        showListInUI(books, mBooksTextView)
    }

    fun onRefreshBtClicked(view: View) {
        mBooksTextView!!.text = ""
        fetchData()
    }
}
