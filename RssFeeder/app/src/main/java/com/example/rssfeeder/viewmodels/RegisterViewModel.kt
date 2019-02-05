package com.example.rssfeeder.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.rssfeeder.repository.UserRepository
import com.example.rssfeeder.services.model.User
import com.example.rssfeeder.util.isValidEmail
import com.example.rssfeeder.util.isValidPhone

/**
 * View model class for registration module
 */
class RegisterViewModel : ViewModel() {

    fun registerUser(user: User): LiveData<User> {
        return UserRepository.register(user)
    }

    fun isValid(email: String, password: String, phone: String, name: String): Boolean {
        return !email.isNullOrEmpty() && email.isValidEmail() && !password.isNullOrEmpty()
                && !phone.isNullOrEmpty() && phone.isValidPhone() && !name.isNullOrEmpty()
    }

}
