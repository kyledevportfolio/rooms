

package com.example.android.persistence.codelab.step1

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

import com.example.android.codelabs.persistence.R
import com.example.android.persistence.codelab.db.AppDatabase
import com.example.android.persistence.codelab.db.User
import com.example.android.persistence.codelab.db.utils.DatabaseInitializer
import java.util.Locale

class UsersActivity : AppCompatActivity() {

    private var mDb: AppDatabase? = null

    private var mYoungUsersTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.db_activity1)

        mYoungUsersTextView = findViewById(R.id.young_users_tv)

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
        // Note: this kind of logic should not be in an activity.
        val sb = StringBuilder()
        val youngUsers = mDb!!.userModel().findUsersYoungerThan(35)
        for (youngUser in youngUsers) {
            sb.append(String.format(Locale.US,
                    "%s, %s (%d)\n", youngUser.lastName, youngUser.name, youngUser.age))
        }
        mYoungUsersTextView!!.text = sb
    }
}
