package com.example.rssfeeder.views.stackoverflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rssfeeder.views.stackoverflow.ui.stackoverflow.StackOverFlowFragment

class StackOverFlowActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.stack_over_flow_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, StackOverFlowFragment.newInstance())
                .commitNow()
        }
    }

}
