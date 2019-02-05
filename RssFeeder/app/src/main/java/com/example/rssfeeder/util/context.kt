package com.example.rssfeeder.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar


const val TAG = "ContextUtil"

/**
 * Extension to kill all Activities and launch the app from scratch
 */
fun Context.restartApp() {
    val intent = packageManager.getLaunchIntentForPackage(packageName)
    intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
    startActivity(intent)
    System.exit(0)
}

/**
 * Extension to start an activity
 * @param cls This is the reference of Activity to launch
 * @param options Additional options for how the Activity should be started
 * @param finish boolean flag to finish the activity starting new activity
 * @param extras to pass value some extra bundled value b/w activities
 */
fun Activity.startActivity(cls: Class<*>, options: Bundle? = null, finish: Boolean = false, extras: Bundle? = null) {
    val intent = Intent(this, cls)
    if (extras != null) {
        intent.putExtras(extras)
    }
    startActivity(intent, options)
    if (finish) {
        finish()
    }
}

/**
 * Extension to start an activity with clear activity stack
 * @param cls This is the reference of Activity to launch
 * @param options Additional options for how the Activity should be started
 * @param extras to pass value some extra bundled value b/w activities
 */
fun Activity.startActivityClearStack(cls: Class<*>, options: Bundle? = null, extras: Bundle? = null) {
    startActivity(Intent(this, cls).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        if (extras != null)
            putExtras(extras)

    }, options)
}

/**
 * Extension to start an activity for result
 * @param cls This is the reference of Activity to launch
 * @param requestCode int: If >= 0, this code will be returned in onActivityResult() when the activity exits.
 * @param options Additional options for how the Activity should be started
 * @param extras to pass value some extra bundled value b/w activities
 */
fun Activity.startActivityForResult(cls: Class<*>, requestCode: Int, options: Bundle? = null, extras: Bundle? = null) {
    val intent = Intent(this, cls)
    if (extras != null) {
        intent.putExtras(extras)
    }
    startActivityForResult(intent, requestCode, options)
}

/**
 * Extension to finish an activity with result ok that is -1
 */
fun Activity.finishResultOK() {
    setResult(Activity.RESULT_OK)
    finish()
}

/**
 * Extension to dismiss the soft keyboard
 */
fun Activity.dismissKeyBoard() {
    if (currentFocus != null) {
        dismissKeyBoard(currentFocus)
    }
}

/**
 * Extension to dismiss the soft keyboard
 * @param view View with current focus
 */
fun Activity.dismissKeyBoard(view: View) {
    try {
        (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
            .hideSoftInputFromWindow(view.windowToken, 0)
    } catch (e: Exception) {
        Log.e(TAG, "Exception dismissing keyboard $e")
    }
}

/**
 * Extension to show a Toast with String message
 * @param text message to be displayed on Toast
 * @param length duration for which Toast will appear on screen SHORT or LONG
 */
fun Context.toast(text: String, length: Int) {
    val toast = Toast.makeText(this, text, length)
    toast.setGravity(Gravity.CENTER, 0, 0)
    toast.show()
}

/**
 * Extension to show a Toast with String message from string.xml
 * @param resId id of string item placed in string.xml
 * @param length duration for which Toast will appear on screen SHORT or LONG
 */
fun Context.toast(resId: Int, length: Int) {
    val toast = Toast.makeText(this, resId, length)
    toast.setGravity(Gravity.CENTER, 0, 0)
    toast.show()
}

/**
 * Extension to show a Toast for short duration with String message
 * @param text message to be displayed on Toast
 */
fun Context.shortToast(text: String) {
    toast(text, Toast.LENGTH_SHORT)
}

/**
 * Extension to show a Toast for short duration with resource id.
 * @param resId id of string message from string.xml file
 */
fun Context.shortToast(resId: Int) {
    toast(resId, Toast.LENGTH_SHORT)
}

/**
 * Extension to show a Toast for long duration with with String message
 * @param text message to be displayed on Toas
 */
fun Context.longToast(text: String) {
    toast(text, Toast.LENGTH_LONG)
}

/**
 * Extension to show a Toast for long duration with resource id.
 * @param resId id of string message from string.xml file
 */
fun Context.longToast(resId: Int) {
    toast(resId, Toast.LENGTH_LONG)
}

/**
 * Extension to show a SnackBar for short duration with resource id.
 * @param view Snackbar will try and find a parent view to hold Snackbar's view from the value given to view
 * @param resId id of string message from string.xml file
 */
fun Context.showShortSnackBar(view: View, resId: Int) {
    Snackbar.make(view, resId, Snackbar.LENGTH_SHORT).show()
}

/**
 * Extension to show a SnackBar for short duration with String Message.
 * @param view Snackbar will try and find a parent view to hold Snackbar's view from the value given to view
 * @param message String message to be displayed in snack bar
 */
fun Context.showShortSnackBar(view: View, message: String) {
    Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
}

/**
 * Extension to show a SnackBar for long duration with String Message.
 * @param view Snackbar will try and find a parent view to hold Snackbar's view from the value given to view
 * @param message String message to be displayed in snack bar
 */
fun Context.showLongSnackBar(view: View, resId: Int) {
    Snackbar.make(view, resId, Snackbar.LENGTH_LONG)
}

/**
 * Extension to check internet connectivity with device
 * @return True or False Boolean based on connectivity to internet
 */
fun Context.isOnline(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    val info = cm!!.activeNetworkInfo ?: return false
    val network = info.detailedState
    return network === NetworkInfo.DetailedState.CONNECTED || network === NetworkInfo.DetailedState.CONNECTING
}