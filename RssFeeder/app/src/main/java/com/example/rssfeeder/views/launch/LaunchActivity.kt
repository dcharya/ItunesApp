package com.example.rssfeeder.views.launch

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.rssfeeder.R
import com.example.rssfeeder.util.AppPreferences
import com.example.rssfeeder.util.startActivity
import com.example.rssfeeder.views.home.HomeActivity
import com.example.rssfeeder.views.login.LoginActivity

class LaunchActivity : AppCompatActivity() {

    private var mDelayHandler: Handler? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

        val mRunnable = Runnable {
            when (AppPreferences.isUserLoggedIn) {
                true -> this.startActivity(HomeActivity::class.java, finish = true)
                false -> this.startActivity(LoginActivity::class.java, finish = true)
            }
        }

        mDelayHandler = Handler()
        mDelayHandler?.postDelayed(mRunnable, LAUNCH_TIME)

    }


    companion object {
        const val LAUNCH_TIME = 3000L
    }
}
