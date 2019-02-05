package com.example.rssfeeder.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rssfeeder.MyApp
import com.example.rssfeeder.services.model.User

/**
 * @UserRepository object to manage user registration and login functionality
 * @author Deepak Kumar
 */
object UserRepository {

    /**
     * @login function to fetch data from data base for register user trying to login into the app
     * @param email: String, email address of user
     * @param password: String, password of user
     *
     * @return LiveData<User> returns a User type live data object.
     */
    fun login(email: String, password: String): LiveData<User> {
        val loginData = MutableLiveData<User>()
        val user = MyApp.database?.userDao()?.getUser(email, password)
        loginData.value = user
        return loginData
    }

    /**
     * @login function to register a new user by adding user data into data base
     * @param user: instance of user class.
     *
     * @return LiveData<User> returns a User type live data object.
     */
    fun register(user: User): LiveData<User> {
        val registerData = MutableLiveData<User>()
        MyApp.database?.userDao()?.insertUser(user)
        registerData.value = user
        return registerData
    }
}