package com.example.rssfeeder.util

import android.util.Patterns


fun String.isValidEmail(): Boolean {
    return !this.isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.isValidPhone() = !this.isNullOrEmpty() && Patterns.PHONE.matcher(this).matches() && this.length == 10