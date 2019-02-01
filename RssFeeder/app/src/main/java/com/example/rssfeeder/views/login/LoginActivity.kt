package com.example.rssfeeder.views.login

import android.app.Activity
import android.os.Bundle
import com.example.rssfeeder.R
import com.example.rssfeeder.util.showShortSnackBar
import com.example.rssfeeder.util.startActivity
import com.example.rssfeeder.views.base.BaseActivity
import com.example.rssfeeder.views.home.HomeActivity
import com.example.rssfeeder.views.login.ui.login.LoginFragment
import com.example.rssfeeder.views.login.ui.login.RegisterFragment
import kotlinx.android.synthetic.main.root_activity_layout.*

class LoginActivity : BaseActivity() {
    private var loginFragment = LoginFragment()
    private lateinit var registerFragment: RegisterFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            showLoginFragment()
        }
    }

    private fun showLoginFragment() {
        title = resources.getString(R.string.login_title)
        showFragment(loginFragment)
    }

    private fun showRegistrerFragment() {
        title = resources.getString(R.string.register_title)
        registerFragment = RegisterFragment()
        showFragment(registerFragment)
    }

    fun launchHomeActivity(activity: Activity) {
        activity.startActivity(HomeActivity::class.java, finish = true)
    }

    override fun onPerformAction(action: String, data: Any?) {
        super.onPerformAction(action, data)
        when (action) {
            LoginFragment.ACTION_SHOW_HOME -> {
                this.startActivity(HomeActivity::class.java, finish = true)
            }
            LoginFragment.ACTION_SHOW_REGISTER -> {

                showRegistrerFragment()
            }
            RegisterFragment.ACTION_SHOW_LOGIN -> {
                data?.let {
                    if (it is String)
                        this.showShortSnackBar(fragmentContainer, it)
                }
                showLoginFragment()
            }
        }
    }

    override fun handleBackPressed() {
        when {
            loginFragment.isVisible -> superOnBackPressed()
            registerFragment.isVisible -> showLoginFragment()
            else -> superOnBackPressed()
        }
    }
}
