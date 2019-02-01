package com.example.rssfeeder.views.home.fragments

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rssfeeder.R
import com.example.rssfeeder.services.model.SongList
import com.example.rssfeeder.util.isOnline
import com.example.rssfeeder.util.showShortSnackBar
import com.example.rssfeeder.viewmodels.HomeViewModel
import com.example.rssfeeder.views.SongsListAdaptar
import com.example.rssfeeder.views.base.BaseFragment
import kotlinx.android.synthetic.main.home_fragment.*


class HomeFragment : BaseFragment() {

    private lateinit var searchView: SearchView
    private lateinit var queryTextListener: SearchView.OnQueryTextListener
    override fun layoutResource() = R.layout.home_fragment
    private lateinit var songsListAdaptar: SongsListAdaptar
    private var currentStatus: SongList? = null

    companion object {
        val TAG = HomeFragment.javaClass.simpleName!!
        val ACTION_SHOW_DETAILS = "$TAG.showdetails"
        val ACTION_SHOW_ERROR = "$TAG.showError"


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    private lateinit var viewModel: HomeViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        songsListAdaptar = SongsListAdaptar(requireContext(), wrapActionListener())
        songListView.apply {
            adapter = songsListAdaptar
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java).apply {
            actionListener = this@HomeFragment.actionListener
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchItem?.let {
            searchView = it.actionView as SearchView
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(activity!!.componentName))
            queryTextListener = object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String): Boolean {
                    Log.i("onQueryTextChange", newText)

                    return false
                }

                override fun onQueryTextSubmit(query: String): Boolean {
                    query?.let {
                        if (!activity?.isOnline()!!) {
                            viewModel.songListData.value = null
                            songsListAdaptar.notifyDataSetChanged()
                            pb_fetch_forecast.visibility = View.VISIBLE

                            viewModel.getSongList(it)
                            initObserver()
                        } else {
                            activity?.showShortSnackBar(home, "Network Error!!!")
                        }
                    }

                    return false
                }
            }
            searchView.setOnQueryTextListener(queryTextListener)
            super.onCreateOptionsMenu(menu, inflater)
        }

    }

    override fun updateView() {

        songsListAdaptar.setData(currentStatus)
    }

    fun initObserver() {
        val songListObserver = Observer<SongList> { songList ->
            songList?.let {
                Log.i(TAG, "${songList.total}")
                currentStatus = songList
                pb_fetch_forecast.visibility = View.GONE
                updateView()

            }
        }
        viewModel.songListData.observe(this, songListObserver)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_search -> false
        else -> super.onOptionsItemSelected(item)
    }
}
