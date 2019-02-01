package com.example.rssfeeder.services.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rssfeeder.services.model.User
import com.example.rssfeeder.services.room.UserDao

@Database(entities = arrayOf(User::class), version = 1)
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}