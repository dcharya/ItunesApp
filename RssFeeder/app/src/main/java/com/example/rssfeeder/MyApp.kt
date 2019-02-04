package com.example.rssfeeder

import android.app.Application
import androidx.room.Room.databaseBuilder
import com.example.rssfeeder.services.room.UserDatabase
import com.example.rssfeeder.util.AppPreferences

class MyApp : Application() {


    override fun onCreate() {
        super.onCreate()
        AppPreferences.init(this)
        database = databaseBuilder(this, UserDatabase::class.java, "users.db").allowMainThreadQueries().build()
    }

    companion object {
        var database: UserDatabase? = null
    }
}