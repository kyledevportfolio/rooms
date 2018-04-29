

package com.example.android.persistence.codelab.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class Book {
    @PrimaryKey
    lateinit var id: String

    var title: String? = null
}
