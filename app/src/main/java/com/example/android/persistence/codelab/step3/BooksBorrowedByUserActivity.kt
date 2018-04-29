

package com.example.android.persistence.codelab.step3

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView

import com.example.android.codelabs.persistence.R
import com.example.android.persistence.codelab.db.Book

class BooksBorrowedByUserActivity : AppCompatActivity() {

    private var mViewModel: BooksBorrowedByUserViewModel? = null

    private var mBooksTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.db_activity)
        mBooksTextView = findViewById(R.id.books_tv)

        // Get a reference to the ViewModel for this screen.
        mViewModel = ViewModelProviders.of(this).get(BooksBorrowedByUserViewModel::class.java)

        // Update the UI whenever there's a change in the ViewModel's data.
        subscribeUiBooks()
    }

    fun onRefreshBtClicked(view: View) {
        mViewModel!!.createDb()
    }

    private fun subscribeUiBooks() {
        // TODO: refresh the list of books when there's new data
        // mViewModel.books.observe(...
    }

    private fun showBooksInUi(books: List<Book>) {
        val sb = StringBuilder()

        for (book in books) {
            sb.append(book.title)
            sb.append("\n")

        }
        mBooksTextView!!.text = sb.toString()
    }
}
