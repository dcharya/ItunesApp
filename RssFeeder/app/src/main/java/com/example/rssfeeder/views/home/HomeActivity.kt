package com.example.rssfeeder.views.home

import android.os.Bundle
import com.example.rssfeeder.R
import com.example.rssfeeder.services.model.Song
import com.example.rssfeeder.util.AppPreferences
import com.example.rssfeeder.util.showShortSnackBar
import com.example.rssfeeder.views.base.BaseActivity
import com.example.rssfeeder.views.home.fragments.DetailFragment
import com.example.rssfeeder.views.home.fragments.HomeFragment
import kotlinx.android.synthetic.main.root_activity_layout.*

class HomeActivity : BaseActivity() {

    private val homeFragment = HomeFragment()
    private val detailFragment by lazy { DetailFragment() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.showShortSnackBar(fragmentContainer, getString(R.string.welcome, AppPreferences.email))
        if (savedInstanceState == null) {
            showHomeFragment()
        }
    }


    fun showHomeFragment() {
        title = "Home Screen"
        showFragment(homeFragment)
    }

    fun showDetailFragment() {
        title = "Details"
        showFragment(detailFragment)
    }

    override fun onPerformAction(action: String, data: Any?) {
        when (action) {
            HomeFragment.ACTION_SHOW_DETAILS ->{
                if(data != null && data is Song) {
                    detailFragment.setData(data)
                    showDetailFragment()
                }
            }
            DetailFragment.ACTION_SHOW_HOME -> showHomeFragment()
            HomeFragment.ACTION_SHOW_ERROR ->
                data?.let { if (it is String) this.showShortSnackBar(fragmentContainer, it) }
        }

    }

    override fun handleBackPressed() {
        when {
            homeFragment.isVisible -> superOnBackPressed()
            detailFragment.isVisible -> showHomeFragment()
            else -> superOnBackPressed()
        }
    }
}
