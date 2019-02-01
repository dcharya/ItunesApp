package com.example.rssfeeder

import android.app.Application
import androidx.room.Room.databaseBuilder
import com.example.rssfeeder.services.room.UserDatabase

class MyApp : Application() {


    override fun onCreate() {
        super.onCreate()
        database = databaseBuilder(this, UserDatabase::class.java,"users.db").allowMainThreadQueries().build()
    }

    companion object {
        var database: UserDatabase? = null
    }
}