package com.example.rssfeeder.services.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

/**
 * Data class to hold user data and Data base table
 * @author Deepak Kumar
 */
@Entity(tableName = "user")
data class User(
    val userName: String = "",
    @PrimaryKey
    val email: String = "",
    val mobile: String = "",
    val password: String = ""
) {

    @Ignore
    constructor() : this("", "", "", "")
}