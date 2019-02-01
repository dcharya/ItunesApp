package com.example.rssfeeder.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.rssfeeder.repository.UserRepository
import com.example.rssfeeder.services.model.User
import com.example.rssfeeder.util.isValidEmail

class LoginViewModel : ViewModel() {
    fun loginUser(email: String, password: String): LiveData<User> {
        return UserRepository.getInstance().login(email, password)
    }

    fun isValid(email: String, password: String): Boolean {
        return !email.isNullOrEmpty() && email.isValidEmail() && !password.isNullOrEmpty()
    }

}

