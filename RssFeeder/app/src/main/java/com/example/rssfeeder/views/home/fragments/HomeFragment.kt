package com.example.rssfeeder.views.home.fragments

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rssfeeder.R
import com.example.rssfeeder.services.model.SongList
import com.example.rssfeeder.util.AppPreferences
import com.example.rssfeeder.util.isOnline
import com.example.rssfeeder.util.restartApp
import com.example.rssfeeder.util.showShortSnackBar
import com.example.rssfeeder.viewmodels.HomeViewModel
import com.example.rssfeeder.views.adapters.SongsListAdapter
import com.example.rssfeeder.views.base.BaseFragment
import com.example.rssfeeder.views.home.HomeActivity
import kotlinx.android.synthetic.main.home_fragment.*


class HomeFragment : BaseFragment() {

    override fun layoutResource() = R.layout.home_fragment

    private lateinit var searchView: SearchView
    private lateinit var queryTextListener: SearchView.OnQueryTextListener
    private lateinit var songsListAdapter: SongsListAdapter

    companion object {
        private val TAG = HomeFragment::class.java.simpleName
        val ACTION_SHOW_DETAILS = "$TAG.showDetails"
        val ACTION_SHOW_ERROR = "$TAG.showError"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    private lateinit var viewModel: HomeViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        songListView.apply {
            adapter = songsListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(activity as HomeActivity).get(HomeViewModel::class.java).apply {
            actionListener = this@HomeFragment.actionListener
            context = requireContext()
        }

        if (viewModel.songListData.value == null)
            search_hint_view.visibility = View.VISIBLE
        else
            search_hint_view.visibility = View.GONE

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        songsListAdapter = SongsListAdapter(requireContext(), wrapActionListener())
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun updateView() {
        /*viewModel.songListData.value?.let {
            if (it.songList?.isNotEmpty() as Boolean) {*/
        songsListAdapter.setData(viewModel.songListData.value)
    }

    fun initObserver() {
        val songListObserver = Observer<SongList> { songList ->
            pb_songList.visibility = View.GONE

            when {
                songList != null -> {
                    searchView.onActionViewCollapsed()
                    search_hint_view.visibility = View.GONE
                }
                else ->
                    search_hint_view.visibility = View.VISIBLE
            }
            updateView()
        }
        viewModel.songListData.observe(this, songListObserver)
        pb_songList.visibility = View.VISIBLE

        search_hint_view.visibility = View.GONE
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
                    return false
                }

                override fun onQueryTextSubmit(query: String): Boolean {
                    if (activity?.isOnline()!!) {
                        viewModel.getSongList(query)
                        initObserver()
                    } else {
                        activity?.showShortSnackBar(home, R.string.err_network)
                    }
                    return false
                }
            }
            searchView.setOnQueryTextListener(queryTextListener)
            super.onCreateOptionsMenu(menu, inflater)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_search -> false
        R.id.action_logout -> {
            AppPreferences.isUserLoggedIn = false
            activity?.restartApp()
            false
        }
        else -> super.onOptionsItemSelected(item)
    }
}
