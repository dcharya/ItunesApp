package com.example.rssfeeder.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rssfeeder.MyApp
import com.example.rssfeeder.services.model.User

class UserRepository {
    companion object {
        private var loginRepository: UserRepository? = null
        //        private var context: Context?=null
        @Synchronized
        @JvmStatic
        fun getInstance(): UserRepository {
            if (loginRepository == null) loginRepository = UserRepository()
            return loginRepository!!
        }
    }

    fun login(email: String, password: String): LiveData<User> {
        val loginData = MutableLiveData<User>()
        val user = MyApp?.database?.userDao()?.getUser(email, password)
        loginData.value = user
        return loginData
    }

    fun register(user: User): LiveData<User> {
        val registerData = MutableLiveData<User>()
        MyApp?.database?.userDao()?.insertUser(user)
        registerData.value = user
        return registerData
    }
}