

package com.example.android.persistence.codelab.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.TypeConverters

import java.util.Date

class LoanWithUserAndBook {
    var id: String? = null

    @ColumnInfo(name = "title")
    var bookTitle: String? = null

    @ColumnInfo(name = "name")
    var userName: String? = null

    @TypeConverters(DateConverter::class)
    var startTime: Date? = null

    @TypeConverters(DateConverter::class)
    var endTime: Date? = null
}
