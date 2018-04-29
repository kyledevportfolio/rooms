
package com.example.android.persistence.codelab.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters

import java.util.Date

@Entity(foreignKeys = arrayOf(ForeignKey(entity = Book::class, parentColumns = arrayOf("id"), childColumns = arrayOf("book_id")),

        ForeignKey(entity = User::class, parentColumns = arrayOf("id"), childColumns = arrayOf("user_id"))))
@TypeConverters(DateConverter::class)
class Loan {
    // Fields can be public or private with getters and setters.
    @PrimaryKey
    lateinit var id: String

    var startTime: Date? = null

    var endTime: Date? = null

    @ColumnInfo(name = "book_id")
    var bookId: String? = null

    @ColumnInfo(name = "user_id")
    var userId: String? = null
}
