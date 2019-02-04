package com.example.rssfeeder.services.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rssfeeder.services.model.User

@Database(entities = arrayOf(User::class), version = 1, exportSchema = false)
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}