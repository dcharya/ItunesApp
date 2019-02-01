package com.example.rssfeeder.views.home.fragments

import android.os.Bundle
import android.view.View
import com.example.rssfeeder.R
import com.example.rssfeeder.services.model.Song
import com.example.rssfeeder.views.base.BaseFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.detail_fragment.*

class DetailFragment : BaseFragment() {
    override fun layoutResource() = R.layout.detail_fragment
    private var currentStatus: Song? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*close_details.setOnClickListener {
            actionListener?.onAction(ACTION_SHOW_HOME, null)
        }*/

        Picasso.with(activity).load(currentStatus?.album?.coverBig).into(close_details)
    }

    fun setData(song: Song) {
        currentStatus = song
    }

    companion object {
        val TAG = DetailFragment.javaClass.simpleName
        val ACTION_SHOW_HOME = "$TAG.homeFragment"
    }
}