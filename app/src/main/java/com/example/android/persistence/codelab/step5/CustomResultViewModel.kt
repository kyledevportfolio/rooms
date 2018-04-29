

package com.example.android.persistence.codelab.step5

import android.app.Application
import android.arch.core.util.Function
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations

import com.example.android.persistence.codelab.db.AppDatabase
import com.example.android.persistence.codelab.db.LoanWithUserAndBook
import com.example.android.persistence.codelab.db.utils.DatabaseInitializer

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


class CustomResultViewModel(application: Application) : AndroidViewModel(application) {

    var loansResult: LiveData<String>? = null
        private set

    private var mDb: AppDatabase? = null

    private val yesterdayDate: Date
        get() {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DATE, -1)
            return calendar.time
        }

    fun createDb() {
        mDb = AppDatabase.getInMemoryDatabase(getApplication())

        // Populate it with initial data
        DatabaseInitializer.populateAsync(mDb!!)

        // Receive changes
        subscribeToDbChanges()
    }

    private fun subscribeToDbChanges() {
        // TODO: Modify this query to show only recent loans from specific user
        val loans = mDb!!.loanModel().findAllWithUserAndBook()

        // Instead of exposing the list of Loans, we can apply a transformation and expose Strings.
        loansResult = Transformations.map(loans
        ) { loansWithUserAndBook ->
            val sb = StringBuilder()
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm",
                    Locale.US)

            for (loan in loansWithUserAndBook) {
                sb.append(String.format("%s\n  (Returned: %s)\n",
                        loan.bookTitle,
                        simpleDateFormat.format(loan.endTime)))
            }
            sb.toString()
        }
    }
}
