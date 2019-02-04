package com.example.rssfeeder.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rssfeeder.MyApp
import com.example.rssfeeder.services.model.User

object UserRepository {

    fun login(email: String, password: String): LiveData<User> {
        val loginData = MutableLiveData<User>()
        val user = MyApp.database?.userDao()?.getUser(email, password)
        loginData.value = user
        return loginData
    }

    fun register(user: User): LiveData<User> {
        val registerData = MutableLiveData<User>()
        MyApp.database?.userDao()?.insertUser(user)
        registerData.value = user
        return registerData
    }
}