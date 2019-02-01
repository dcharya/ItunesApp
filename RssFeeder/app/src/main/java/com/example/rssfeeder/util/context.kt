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

fun Activity.startActivityClearStack(cls: Class<*>, options: Bundle? = null, extras: Bundle? = null) {
    startActivity(Intent(this, cls).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        if (extras != null)
            putExtras(extras)

    }, options)
}

fun Activity.startActivityForResult(cls: Class<*>, requestCode: Int, options: Bundle? = null, extras: Bundle? = null) {
    val intent = Intent(this, cls)
    if (extras != null) {
        intent.putExtras(extras)
    }
    startActivityForResult(intent, requestCode, options)
}

fun Activity.finishResultOK() {
    setResult(Activity.RESULT_OK)
    finish()
}

fun Activity.dismissKeyBoard() {
    if (currentFocus != null) {
        dismissKeyBoard(currentFocus)
    }
}

fun Activity.dismissKeyBoard(view: View) {
    try {
        (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
            .hideSoftInputFromWindow(view.windowToken, 0)
    } catch (e: Exception) {
        Log.e(TAG, "Exception dismissing keyboard $e")
    }
}

fun Context.toast(text: String, length: Int) {
    val toast = Toast.makeText(this, text, length)
    toast.setGravity(Gravity.CENTER, 0, 0)
    toast.show()
}

fun Context.toast(resId: Int, length: Int) {
    val toast = Toast.makeText(this, resId, length)
    toast.setGravity(Gravity.CENTER, 0, 0)
    toast.show()
}

fun Context.shortToast(text: String) {
    toast(text, Toast.LENGTH_SHORT)
}

fun Context.shortToast(resId: Int) {
    toast(resId, Toast.LENGTH_SHORT)
}

fun Context.longToast(text: String) {
    toast(text, Toast.LENGTH_LONG)
}

fun Context.longToast(resId: Int) {
    toast(resId, Toast.LENGTH_LONG)
}

fun Context.showShortSnackBar(view: View, resId: Int) {
    Snackbar.make(view, resId, Snackbar.LENGTH_SHORT).show()
}

fun Context.showShortSnackBar(view: View, message: String) {
    Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
}

fun Context.showLongSnackBar(view: View, resId: Int) {
    Snackbar.make(view, resId, Snackbar.LENGTH_LONG)
}

fun Context.isOnline(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    val info = cm!!.activeNetworkInfo ?: return false
    val network = info.detailedState
    return network === NetworkInfo.State.CONNECTED || network === NetworkInfo.State.CONNECTING
}