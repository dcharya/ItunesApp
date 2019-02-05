package com.example.rssfeeder.views.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.rssfeeder.R
import com.example.rssfeeder.util.ActionListener

/**
 * @BaseActivity all other activities extends this class contains base functionality of an activity
 * @author Deepak Kumar
 */
open class BaseActivity : AppCompatActivity(), ActionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.root_activity_layout)
    }

    open fun showFragment(fragment: Fragment, addToBackStack: Boolean = false) {
        val fragmentTransaction = supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment)
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.javaClass.simpleName)
        }
        fragmentTransaction.commit()

    }

    override fun onAction(action: String, data: Any?) {
        onPerformAction(action, data)
    }

    fun superOnBackPressed() {
        super.onBackPressed()
    }

    override fun onBackPressed() {
        handleBackPressed()
    }

    open fun onPerformAction(action: String, data: Any?) {}

    open fun handleBackPressed() {}
}