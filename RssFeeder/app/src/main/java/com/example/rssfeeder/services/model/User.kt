package com.example.rssfeeder.services.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    val userName: String = "",
    @PrimaryKey
    val email: String = "",
    val mobile: String = "",
    val password: String = "",
    var isLoggedIn: Boolean = false
) {

    @Ignore
    constructor() : this("", "", "", "", false)
}