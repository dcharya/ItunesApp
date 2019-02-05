package com.example.rssfeeder.util

import android.content.Context
import android.content.SharedPreferences

/**
 * @AppPreferences This is an object to manage SharedPreference in Application
 * @author Deepak Kumar
 */
object AppPreferences {
    private const val NAME = "ITUNE"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    // list of app specific preferences
    private val IS_USER_LOGGED_IN = Pair("is_logged_in", false)
    private val USER_EMAIL = Pair("user_email", "")
    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    /**
     * SharedPreferences extension function, so we won't need to call edit() and apply()
     * ourselves on every SharedPreferences operation.
     */
    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.commit()
    }

    /**
     * To store and retrieve login status of user
     */
    var isUserLoggedIn: Boolean
        // custom getter to get a preference of a desired type, with a predefined default value
        get() = preferences.getBoolean(IS_USER_LOGGED_IN.first, IS_USER_LOGGED_IN.second)
        // custom setter to save a preference back to preferences file
        set(value) = preferences.edit {
            it.putBoolean(IS_USER_LOGGED_IN.first, value)
        }

    /**
     * To store and retrieve email address of logged in user
     */
    var email: String
        get() = preferences.getString(USER_EMAIL.first, USER_EMAIL.second)
        set(value) {
            preferences.edit {
                it.putString(USER_EMAIL.first, value)
            }
        }
}