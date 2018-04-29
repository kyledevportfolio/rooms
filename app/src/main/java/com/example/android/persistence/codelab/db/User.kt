

package com.example.android.persistence.codelab.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity
class User {
    @PrimaryKey
    lateinit var id: String

    var name: String? = null

    var lastName: String? = null

    var age: Int = 0
}
