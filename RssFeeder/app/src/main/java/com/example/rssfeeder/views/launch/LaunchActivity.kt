package com.example.rssfeeder.views.launch

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.rssfeeder.R
import com.example.rssfeeder.util.startActivity
import com.example.rssfeeder.views.login.LoginActivity

class LaunchActivity : AppCompatActivity() {

    private var mDelayHandler: Handler? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

        val mRunnable = Runnable {
            this.startActivity(LoginActivity::class.java, finish = true)
        }

        mDelayHandler = Handler()
        mDelayHandler?.postDelayed(mRunnable, LAUNCH_TIME)

    }


    companion object {
        const val LAUNCH_TIME = 3000L
    }
}
