package com.example.rssfeeder.util

import android.util.Patterns

/**
 * Extension to check a string entered is a valid email address or not
 * @return Boolean
 */
fun String.isValidEmail(): Boolean =
    !this.isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()


/**
 * Extension to check a string entered is a valid Phone number or not
 * @return Boolean
 */
fun String.isValidPhone() = !this.isNullOrEmpty() && Patterns.PHONE.matcher(this).matches() && this.length == 10